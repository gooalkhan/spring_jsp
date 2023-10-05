import requests
import threading
import time
import sys

if __name__ == '__main__':

    try:
        print("%s process starts" % sys.argv[0])
        for i in range(10):
            print(i)
            time.sleep(0.5)

        print("process ends successfully")

    except Exception as e:
        print(e)
        print("process ends with error")
