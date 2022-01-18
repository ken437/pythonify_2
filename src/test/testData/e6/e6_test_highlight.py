class A:
    def __init__(self):
        print("Initialization")

class B(A):
    def new_method(self):
        print("Placeholder")

    def fors(self):
        """
        x = []
        for i in range(10):
           x.append(i)
        """
        y = [4, 5, 6, 7]
        z = [i for i in y]
        print(z, y)
        x = []
        <warning>
        for i in range(10):
            x.append(i)
        </warning>

        w = []
        for i in range(10):
            y.append(i)

        w = []
        for i in range(10):
            print(w)
            w.append(i)

        for i in range(10):
            w.append(i)
            print(w)

        for i in range(10):
            pass