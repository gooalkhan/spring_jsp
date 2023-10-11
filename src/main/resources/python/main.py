import sys

if sys.argv[1] == "dev":
    from H2DB import MyDB
else:
    from MySQLDB import MyDB

db = MyDB.get_instance()

data = db.getAll()

for row in data:
    print(row)
