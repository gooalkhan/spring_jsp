import requests
import threading
import time
import sys

if __name__ == '__main__':

    try:
        print("from python - %s python process starts" % sys.argv[0])
        for i in range(10):
            print(i)
            time.sleep(0.5)

        print("from python - python process ends successfully")

    except Exception as e:
        print(e)
        print("from python - python process ends with error")