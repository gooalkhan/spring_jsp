import platform
import sys
from jinja2 import Environment, FileSystemLoader
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
COMMON_FILENAME = "application.properties"

if platform.system().lower() == 'windows':
    DEV_FILENAME = "\\application-dev.properties"
    PROD_FILENAME = "\\application-prod.properties"
    COMMON_FILENAME = "\\application.properties"
else:
    DEV_FILENAME = "/application-dev.properties"
    PROD_FILENAME = "/application-prod.properties"
    COMMON_FILENAME = "/application.properties"

LIB_PATH = sys.argv[1]

DEV_CONFIG = get_config(LIB_PATH + DEV_FILENAME)
PROD_CONFIG = get_config(LIB_PATH + PROD_FILENAME)
COMMON_CONFIG = get_config(LIB_PATH + COMMON_FILENAME)
SPRING_CONFIG = {"dev": DEV_CONFIG, "prod": PROD_CONFIG}

IMG_PATH = COMMON_CONFIG["python.images.path.windows"] if platform.system().lower() == 'windows' else COMMON_CONFIG["python.images.path.linux"]

TEMPLATE_DIR = LIB_PATH + '\\python\\templates' if platform.system().lower() == 'windows' else LIB_PATH + '/python/templates'

# Jinja2 환경을 설정합니다.
JINJA_ENV = Environment(loader=FileSystemLoader(TEMPLATE_DIR))

CURRENT_CONFIG = SPRING_CONFIG[str(sys.argv[2])]

if __name__ == "__main__":
    print(CURRENT_CONFIG)
