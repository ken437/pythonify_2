import getpass

def an_api1(password):
    print(password)

def an_api2(pass_word):
    print(pass_word)

def an_api3(innocent_param):
    print(innocent_param)

def three_params(okay, password, also_okay):
    print(okay, password, also_okay)

class A:
    def a_api(self, pass_word):
        print(pass_word)

def indirect_hardcoding():
    im_not_a_password = "Sensitive, hardcoded password"
    nothing_to_see_here = "Sensitive, hardcoded password"
    innocent_var = getpass.getpass("Password: ")

    <warning>an_api1(password=im_not_a_password)</warning>
    an_api3(innocent_param=im_not_a_password)
    an_api1(password=innocent_var)

    # Let's see if the for loop blocks the analysis
    for i in range(10):
        if i % 3 == 0:
            print(i)

    <warning>an_api2(pass_word=nothing_to_see_here)</warning>

    a = 5
    if a > 2:
        if a < 7:
            <warning>an_api1(password=im_not_a_password)</warning>

hardcoded = "password"
not_hardcoded = getpass.getpass("Password: ")
never_used = "Don't use me"

a = 5
if a > 2:
    if a < 7:
        <warning>an_api1(password=hardcoded)</warning>
        an_api3(innocent_param=hardcoded)
        an_api1(password=not_hardcoded)

also_hardcoded = "password"

def func10(param):
    print("Hello")
    # comment
    if a + 4 > 18:
        print("placeholder")
        # comment
    for i in range(100):
        for j in range(2):
            if a > 3:
                print("placeholder")
            else:
                # comment
                <warning>an_api1(password=also_hardcoded)</warning>
                an_a = A()
                <warning>an_a.a_api(pass_word=also_hardcoded)</warning>
                three_params(okay=also_hardcoded, password=param, also_okay=also_hardcoded)
                <warning>three_params(okay=param, password=also_hardcoded, also_okay="Hardcoded!")</warning>