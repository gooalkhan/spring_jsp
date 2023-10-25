import sys
from jinja2 import Template
import uuid
import time

if sys.argv[2] == "dev":
    from H2DB import MyDB
else:
    from MySQLDB import MyDB

db = MyDB.get_instance()
bookid = sys.argv[3]


def render_template(bookid):
    # Jinja2 템플릿을 정의합니다.
    template_string = "<h1> {{ bookid }} keyword analysis called</h1> <img src=/images/peb.jpg>"
    template = Template(template_string)

    # 템플릿을 렌더링합니다. 여기에서는 name 변수를 전달합니다.
    rendered_template = template.render(bookid=str(bookid))
    rendered_template = rendered_template.replace("<", "&lt;")
    rendered_template = rendered_template.replace(">", "&gt;")

    # 렌더링된 템플릿을 문자열로 반환합니다.
    return rendered_template


def get_keyword_analysis(bookid: int) -> None:
    books = db.getBook(bookid)
    if len(books) == 0:
        print("no book found", file=sys.stderr)
    else:
        book = books[0]

        myUUID = str(uuid.uuid4())
        string_template = render_template(book[1])

        time.sleep(5)
        db.insert(myUUID, bookid, "키워드", string_template)
        # print("status:complete")


if __name__ == "__main__":
    get_keyword_analysis(bookid)
