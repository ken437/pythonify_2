from math import *

import sqlite3
import Crypto.Cipher
from Crypto.Cipher import AES
import hashlib
import random

def hi():
    a = "Hello, my name is Bob"
    print(sqrt(2))
    b = [1, 2, 3]

    f1 = open("example.txt", "r")
    print(f1.read())
    # not closed

    f2 = open("example.txt", "w")
    f2.write("Hi")
    # not closed

    f3 = open("example.txt", "r")
    print(f3.read())
    f3.close()

    password = "Hardcoded password"
    key = "Hardcoded key"
    password1 = random.choice(["a", "b", "c"]) * 10  # random should not be used to generate a password
    print(password)
    print(password1)
    print(key)

    con = sqlite3.connect("example.db")
    cur = con.cursor()

    user_input = input("Please enter some malicious input")
    cur.execute("CREATE TABLE " + user_input)
    cur.execute("CREATE TABLE %s" % user_input)
    exec(user_input)
    exec("not malicious")

    con.commit()
    con.close()

    key = b"Hello!"
    ciphers = []
    ciphers += AES.new(key, AES.mode_ECB)
    ciphers += AES.new(key, AES.mode_CBC, "\0")
    ciphers += AES.new(key, AES.mode_CBC)
    ciphers += AES.new(str(random.randint(1000)), AES.mode_CBC)
    ciphers += AES.new(b"1000", AES.mode_CBC)
    print(ciphers)

    hashlib.sha1()
    hashlib.sha()
    hashlib.md5()
    hashlib.md4()

    Crypto.Cipher.SHA.new()
    Crypto.Cipher.RC2.new()
    Crypto.Cipher.Blowfish.new()
    Crypto.Cipher.RC4.new()

    rand = random.random()
    print(rand)