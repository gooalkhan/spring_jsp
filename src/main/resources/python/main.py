import subprocess
import sys
import os

URI = "jdbc:h2:tcp://localhost:9092/mem:testdb"
ID = "sa"
PW = ""
LIB_PATH = os.getcwd() + "\\build\\resources\\main\\python\\lib\\h2-2.2.224.jar"

try:
    import jaydebeapi
except ImportError:
    print("Error importing jaydebeapi")
    subprocess.check_call([sys.executable, "-m", "pip", "install", 'jaydebeapi'])
    import jaydebeapi
finally:
    try:
        print("%s process starts" % sys.argv[0])

        print("file exists %s in %s" % (os.path.exists(LIB_PATH), LIB_PATH))

        with jaydebeapi.connect('org.h2.Driver', URI, [ID, PW], LIB_PATH) as conn:
            with conn.cursor() as curs:
                curs.execute("select * from MEMBERTBL")
                data = curs.fetchall()
                for row in data:
                    print(row)

        print("process ends successfully")

    except Exception as e:
        print(e)
        print("process ends with error")
