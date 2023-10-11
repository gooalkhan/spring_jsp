from config import PROD_CONFIG
import re
import subprocess
import sys

URI = PROD_CONFIG["spring.datasource.url"]
ID = PROD_CONFIG["spring.datasource.username"]
PW = PROD_CONFIG["spring.datasource.password"]

__raw_data = re.findall("mysql://(.+):(\d+)/(.+)", URI)[0]
#print(__raw_data, ID, PW)

DB_CONFIG = {
    "host": __raw_data[0],  # re.findall("mysql://(.+):", "mysql://localhost:3306/proddb")[0] -> localhost
    "port": __raw_data[1],
    "database": __raw_data[2],
    "user": ID,
    "password": PW
}

TOTAL_MAX_CONNECTIONS = 5

try:
    import mysql.connector.pooling

except ImportError:
    print("Error importing mysql.connector")
    subprocess.check_call([sys.executable, "-m", "pip", "install", 'mysql-connector-python'])
    import mysql.connector.pooling

finally:

    class MyDB:
        __instance = None

        def __init__(self):
            self.__db_pool = mysql.connector.pooling.MySQLConnectionPool(pool_size=TOTAL_MAX_CONNECTIONS, **DB_CONFIG)

        def getAll(self):
            result = []
            conn = self.__db_pool.get_connection()
            with conn.cursor() as curs:
                curs.execute("select * from membertbl")
                data = curs.fetchall()
                for row in data:
                    result.append(row)
            conn.close()
            return result

        @classmethod
        def __get_instance(cls):
            return cls.__instance

        @classmethod
        def get_instance(cls):
            cls.__instance = cls()
            cls.get_instance = cls.__get_instance
            return cls.__instance
