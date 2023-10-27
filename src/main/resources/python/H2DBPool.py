import subprocess
import sys
from H2DB import jaydebeapi, URI, ID, PW, LIB_PATH
from config import TOTAL_MAX_CONNECTIONS
from contextlib import contextmanager

try:
    from dbutils.pooled_db import PooledDB

except ImportError:
    print("Error importing dbutils")
    subprocess.check_call([sys.executable, "-m", "pip", "install", 'dbutils'])
    from dbutils.pooled_db import PooledDB

finally:

    H2_DB_CONFIG = {
        "mincached": TOTAL_MAX_CONNECTIONS,
        "maxcached": TOTAL_MAX_CONNECTIONS,
        "jclassname": "org.h2.Driver",
        "url": URI,
        "driver_args": [ID, PW],
        "jars": LIB_PATH
    }


    class MyDB:
        __instance = None

        def __init__(self):
            self.db_pool = PooledDB(creator=jaydebeapi.connect, **H2_DB_CONFIG)

        @contextmanager
        def get_connection(self):
            try:
                conn = self.__get_connection()
                yield conn
            finally:
                conn.close()

        def __get_connection(self):
            return self.db_pool.connection()

        def insert(self, jobuid, bookid, productid, template):
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("insert into python (JOBUID, BOOKID, PRODUCTID, STRINGTEMPLATE) values ('%s', %s, '%s', '%s')" % (jobuid, bookid, productid, template))
                conn.commit()

        def updateBook(self, bookid, review_count, preference_count, series_count, is_completed, is_gidamu, series_last_updated):
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("update booktbl set review_count = %s, preference_count = %s, series_count = %s, is_completed = %s, is_gidamu = %s, series_last_updated = %s where bookid = %s" % (review_count, preference_count, series_count, is_completed, is_gidamu, series_last_updated, bookid))
                conn.commit()

        def getBook(self, bookid):
            result = []
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("select * from booktbl where bookid = %s" % bookid)
                    data = curs.fetchall()
                    for row in data:
                        result.append(row)
                return result

        def getBooksToBeUpdated(self):
            result = []
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("select * from booktbl where is_completed = 0 and BOOK_LAST_UPDATE < (CURRENT_TIMESTAMP - INTERVAL '1' HOUR);")
                    data = curs.fetchall()
                    for row in data:
                        result.append(row)
                return result

        @classmethod
        def __get_instance(cls):
            return cls.__instance

        @classmethod
        def get_instance(cls):
            cls.__instance = cls()
            cls.get_instance = cls.__get_instance
            return cls.__instance
