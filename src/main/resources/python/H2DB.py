import subprocess
import sys
import os
import platform
from config import DEV_CONFIG

WIN_LIB_PATH = os.getcwd() + "\\build\\resources\\main\\python\\lib\\h2-2.2.224.jar"
LINUX_LIB_PATH = os.getcwd() + "/build/resources/main/python/lib/h2-2.2.224.jar"
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

        def __get_connection(self):
            return jaydebeapi.connect('org.h2.Driver', URI, [ID, PW], LIB_PATH)

        def getAll(self):
            result = []
            conn = self.__get_connection()
            with conn.cursor() as curs:
                curs.execute("select * from MEMBERTBL")
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
