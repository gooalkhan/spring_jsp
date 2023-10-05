import requests
import threading
import time

try:
    print("from python - python process starts")
    for i in range(100):
        print(i)

    print("from python - python process ends successfully")

except Exception as e:
    print(e)
    print("from python - python process ends with error")