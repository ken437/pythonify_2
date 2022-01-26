from math import *

import sqlite3
import Crypto.Cipher
from Crypto.Cipher import AES
import hashlib
import random
from secrets import compare_digest
from socket import socket


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
    pwd = "Hardcoded password"
    passwd = "Hardcoded password"
    pass_word = "Hardcoded password"
    passWord = "Hardcoded password"
    password1 = "Hardcoded password"
    password2 = "Hardcoded password"
    print(password, pwd, passwd, pass_word, passWord, password1, password2)
    key = "Hardcoded key"
    password1 = random.choice(["a", "b", "c"]) * 10  # random should not be used to generate a password
    print(password)
    print(password1)
    print(key)

    if password == "Secret password":
        print("Executing sensitive commands")
    if "Secret password" == password:
        print("Executing sensitive commands")
    if compare_digest(password, "Secret password"):
        print("Executing sensitive commands")
    if compare_digest("Secret password", password):
        print("Executing sensitive commands")

    passvar = input("Please enter a sensitive password that everyone can see on the terminal: ")
    passvar1 = input("Password: ")
    print(passvar, passvar1)

    ip_addr = "111.111.111.111"
    ip = "111.111.111.111"
    sock = socket.socket()
    sock.bind(ip, 9090)
    hostname = "Hardcoded!"
    print(ip_addr, hostname)

    user_name = "Username"
    username = "Username"
    print(user_name, username)

    con = sqlite3.connect("example.db")
    cur = con.cursor()

    user_input = input("Please enter some malicious input")
    cur.execute("CREATE TABLE " + user_input)
    cur.execute("CREATE TABLE %s" % user_input)
    exec(user_input)
    exec("not malicious")
    safe_var = "not malicious"
    exec(safe_var)
    exec("not" + "malicious")

    con.commit()
    con.close()

    key = b"Hello!"
    ciphers = []
    ciphers += AES.new(key, AES.MODE_ECB)
    ciphers += AES.new(key, AES.MODE_CBC, "\0")
    ciphers += AES.new(key, AES.MODE_CBC)
    ciphers += AES.new(b"1000", AES.MODE_CBC)
    print(ciphers)

    hashlib.sha1()
    hashlib.md5()

    Crypto.Cipher.ARC4.new()
    Crypto.Cipher.Blowfish.new()

    rand = random.random()
    print(rand)