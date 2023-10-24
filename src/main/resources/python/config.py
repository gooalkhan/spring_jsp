import os
import platform
import sys
'''
    This file is used to read the spring application config file and return a dictionary
'''

def get_config(filepath):
    with open(filepath) as f:
        lines = f.read().splitlines()
        lines = filter(lambda x: not x.startswith('#') and '=' in x, lines)
        dic = {}
        for line in lines:
            key, value = line.split('=')
            dic[key] = value
        return dic


DEV_FILENAME = "application-dev.properties"
PROD_FILENAME = "application-prod.properties"
# WIN_LIB_PATH = os.getcwd() + "\\build\\resources\\main\\"
# LINUX_LIB_PATH = os.getcwd() + "/build/resources/main/"
# LIB_PATH = WIN_LIB_PATH if platform.system().lower() == 'windows' else LINUX_LIB_PATH

if platform.system().lower() == 'windows':
    DEV_FILENAME = "\\application-dev.properties"
    PROD_FILENAME = "\\application-prod.properties"
else:
    DEV_FILENAME = "/application-dev.properties"
    PROD_FILENAME = "/application-prod.properties"

LIB_PATH = sys.argv[1]

DEV_CONFIG = get_config(LIB_PATH + DEV_FILENAME)
PROD_CONFIG = get_config(LIB_PATH + PROD_FILENAME)
SPRING_CONFIG = {"dev": DEV_CONFIG, "prod": PROD_CONFIG}

CURRENT_CONFIG = SPRING_CONFIG[str(sys.argv[2])]

if __name__ == "__main__":
    print(CURRENT_CONFIG)
