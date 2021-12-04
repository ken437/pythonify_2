the_glob = 1

def oneFunc():
    a = 1
    return 2

def twoFunc():
    <warning>global the_glob</warning>
    s = "This string containing the word 'global' should not be highlighted!"
    the_glob = 2