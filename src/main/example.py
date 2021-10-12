import sqlite3
import sys
from secrets import compare_digest


def aFunc():
    pass


if __name__ == "__main__":
    cursor = sqlite3.connect("example.db").cursor()
    user_data = sys.argv[1]
    cursor.execute("CREATE TABLE %s" % user_data)  # SQL injection

    password = ""
    if "Password" == password:  # Triggers the Python vulnerability detection plugin I installed
        pass
