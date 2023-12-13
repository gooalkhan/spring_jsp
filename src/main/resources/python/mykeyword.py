import sysconfig

print(sysconfig.get_path("platlib"))

import sys
import uuid
import time
import pandas as pd
from config import IMG_PATH, JINJA_ENV

if sys.argv[2] == "dev":
    from H2DBPool import MyDB
else:
    from MySQLDB import MyDB

db = MyDB.get_instance()
bookid = sys.argv[3]


def get_genre_data(bookid) -> pd.DataFrame:
    # 해당하는 책의 장르를 가져오기
    category = ""
    is_adult_only = ""
    with db.get_connection() as conn:
        categoryDf = pd.read_sql_query('select category, is_adult_only from booktbl where bookid = %s' % bookid, conn)
    categoryDf.columns = categoryDf.columns.str.lower()
    category = categoryDf["category"][0]
    is_adult_only = categoryDf["is_adult_only"][0]

    PICKLE_PATH = IMG_PATH + '%s_%s.pkl' % (category, 'keyword')

    # 피클이 있는지 확인 후 있으면 df로 변환 후 리턴
    try:
        keywordDf = pd.read_pickle(PICKLE_PATH)
        return keywordDf
    except FileNotFoundError:
        pass

    # 장르별 책과 장르별 키워드를 가져오기
    with db.get_connection() as conn:
        bookDf = pd.read_sql_query(
            "select * from booktbl where category = '%s' and is_adult_only = '%s'" % (category, is_adult_only), conn)
        bookDf.columns = bookDf.columns.str.lower()
        keywordDf = pd.read_sql_query('select * from keywordtbl', conn)
        keywordDf.columns = keywordDf.columns.str.lower()

    # 합치기
    mergedDf = pd.merge(bookDf, keywordDf, on='bookid')

    # 합친 후 불용 키워드 제거 - 이북, 작가명, 출판사명 등
    stop_words = {category, '연재중', '서양풍 로판', '로판 웹소설', 'ebook', '전자책', '서양풍', '기다리면무료', '평점4점이상', '리뷰1000개이상',
                  '별점1000개이상', '별점3000개이상', '리뷰3000개이상', '별점500개이상', '리뷰500개이상', '리뷰100개이상', '별점100개이상'}
    stop_words_author = mergedDf['author'].unique()
    stop_words_publisher = mergedDf['publisher'].unique()
    stop_words.update(stop_words_author)
    stop_words.update(stop_words_publisher)
    filteredDf = mergedDf[~mergedDf['keyword'].isin(stop_words)]

    # 키워드별 선작 리뷰수 평균 구하기
    keywordDf = filteredDf[['keyword', 'review_count', 'preference_count']]
    keywordDf = keywordDf.groupby('keyword').mean()

    # 장르 평균 키워드 선작, 리뷰수 구하기
    keywordMean = keywordDf.mean()
    print(keywordMean)

    # 키워드별 선작 리뷰수 평균 데이터프레임에 장르 평균 추가
    new_row = pd.DataFrame({'review_count': [keywordMean.iloc[0]], 'preference_count': [keywordMean.iloc[1]]},
                           index=['장르 평균'])
    keywordDf = pd.concat([keywordDf, new_row])
    #keywordDf = keywordDf.append(new_row)

    keywordDf['review_count'] = keywordDf['review_count'].astype(int)
    keywordDf['preference_count'] = keywordDf['preference_count'].astype(int)

    keywordDf.index.name = 'keyword'

    # 피클로 저장
    keywordDf.to_pickle(PICKLE_PATH)

    return keywordDf


def get_keyword_analysis(bookid: str) -> pd.DataFrame:
    # 장르데이터 가져오기
    keywordDf = get_genre_data(bookid)

    # 해당 책의 키워드 가져오기
    with db.get_connection() as conn:
        myKeywordDf = pd.read_sql_query('select * from keywordtbl where bookid = %s' % bookid, conn)
        myKeywordDf.columns = myKeywordDf.columns.str.lower()

        myKeywordDf = myKeywordDf[['keyword']]

    # 장르평균 키워드 더하기
    myKeywordDf.loc[len(myKeywordDf)] = '장르 평균'

    # 키워드 합치기
    mergedDf = pd.merge(keywordDf, myKeywordDf, on='keyword')

    return mergedDf


def render_template(bookid):
    keywordDf = get_keyword_analysis(bookid)

    genre_avg = keywordDf[keywordDf['keyword'] == "장르 평균"].to_dict(orient='records')[0]
    keywordDf = keywordDf[keywordDf['keyword'] != "장르 평균"]

    data_list = keywordDf.to_dict(orient='records')

    data = {
        "genre_avg": genre_avg,
        "data_list": data_list
    }

    rendered_template = JINJA_ENV.get_template('keyword_scatter.jinja').render(data=data)
    rendered_template = rendered_template.replace("<", "&lt;")
    rendered_template = rendered_template.replace(">", "&gt;")
    rendered_template = rendered_template.replace("'", "&apos;")

    return rendered_template


def put_keyword_analysis(bookid: str) -> None:
    books = db.getBook(bookid)
    if len(books) == 0:
        print("no book found", file=sys.stderr)
    else:
        book = books[0]

        myUUID = str(uuid.uuid4())
        string_template = render_template(book[0])

        time.sleep(5)
        db.insert(myUUID, bookid, "키워드", string_template)


if __name__ == "__main__":
    put_keyword_analysis(bookid)
