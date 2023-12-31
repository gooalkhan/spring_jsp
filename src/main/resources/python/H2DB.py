import subprocess
import sys
import platform
from contextlib import contextmanager
from config import DEV_CONFIG, LIB_PATH

WIN_LIB_PATH = LIB_PATH + "\\python\\lib\\h2-2.2.224.jar"
LINUX_LIB_PATH = LIB_PATH + "/python/lib/h2-2.2.224.jar"
LIB_PATH = WIN_LIB_PATH if platform.system().lower() == 'windows' else LINUX_LIB_PATH

URI = "jdbc:h2:tcp://localhost:9092/" + DEV_CONFIG["spring.datasource.url"].replace("jdbc:h2:", "")
ID = DEV_CONFIG["spring.datasource.username"]
PW = DEV_CONFIG["spring.datasource.password"]

try:
    import jaydebeapi

except ImportError:
    print("Error importing jaydebeapi")
    subprocess.check_call([sys.executable, "-m", "pip", "install", 'jaydebeapi'])
    import jaydebeapi

finally:

    class MyDB:
        __instance = None

        def __init__(self):
            pass

        @contextmanager
        def get_connection(self):
            conn = self.__get_connection()
            try:
                yield conn
            finally:
                conn.close()

        def __get_connection(self):
            return jaydebeapi.connect('org.h2.Driver', URI, [ID, PW], LIB_PATH)

        def getBook(self, bookid):
            result = []
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("select * from booktbl where bookid = %s" % bookid)
                    data = curs.fetchall()
                    for row in data:
                        result.append(row)
                return result

        def insert(self, jobuid, bookid, productid, template):
            with self.get_connection() as conn:
                with conn.cursor() as curs:
                    curs.execute("insert into python (JOBUID, BOOKID, PRODUCTID, STRINGTEMPLATE) values ('%s', %s, '%s', '%s')" % (jobuid, bookid, productid, template))
                conn.commit()

        @classmethod
        def __get_instance(cls):
            return cls.__instance

        @classmethod
        def get_instance(cls):
            cls.__instance = cls()
            cls.get_instance = cls.__get_instance
            return cls.__instance
