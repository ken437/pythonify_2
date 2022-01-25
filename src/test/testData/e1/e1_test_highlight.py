def func1():
    a = [1, 2, 3]
    b = <warning>a[len(a) - 1]</warning>
    c = a[-1]
    d = <warning>a [ len( a )  -
                     2]</warning>
    e = a[len(a) + 1]
    f = <warning>a[len(a) -10]</warning>
    # a[len(a) - 1]
    s = "a[len(a) - 1]"

class LenDefiner:
    def len(self):
        print("I don't find the length of anything!")
        return 1

    def dont_highlight_here(self):
        a = [1, 2, 3]
        a = a[self.len(a) - 1]