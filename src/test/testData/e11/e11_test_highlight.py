import getpass

def an_api1(password):
    print(password)

def an_api2(pass_word):
    print(pass_word)

def an_api3(innocent_param):
    print(innocent_param)

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