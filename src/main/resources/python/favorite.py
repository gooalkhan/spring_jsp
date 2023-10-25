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

if __name__ == "__main__":
    time.sleep(5)
    raise Exception("favorite analyis failed")