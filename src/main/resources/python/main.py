import sys
from jinja2 import Template

if sys.argv[1] == "dev":
    from H2DB import MyDB
else:
    from MySQLDB import MyDB

db = MyDB.get_instance()

