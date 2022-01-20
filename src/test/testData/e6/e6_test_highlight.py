class A:
    def __init__(self):
        print("Initialization")

class B(A):
    def new_method(self):
        print("Placeholder")

    def tricky_whitespace(self):

        for i in range(10):
            pass

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
        <warning>for i in range(10):
            x.append(i)</warning>

        w = []
        for i in range(10):
            y.append(i)

        w = []
        for i in range(10):
            print(w)
            w.append(i)

        w = [1, 2, 3]

        for i in range(10):
            w.append(i)

        for i in range(10):
            w.append(i)
            print(w)

        for i in range(10):
            pass

        for i in range(10):
            w.append(i)

        self.field = []
        <warning>for i in range(10):
            self.field.append(i * i)</warning>

        doubles = [(1, 2), (3, 4), (5, 6)]
        m = []
        <warning>for first, second in doubles:
            m.append("hi")</warning>

        m = []
        <warning>for i in range(2, 10):
            # This comment shouldn't stop the highlighting
            m.append(i)</warning>

        m = []
        <warning>for i in range(2, 10):
            m.append(i)
            # This comment shouldn't, either</warning>

        m = []
        <warning>for i in range(2, 10):
            # And this

            m.append(i)</warning>

        m = []
        <warning>for i in range(2, 10):
            m.append(i)

            # And this</warning>

        superlonglistname = []
        for k in [1, 2, 3]:
            superlonglistname.append(k, 1)

        m = []
        <warning>for i in [1, 2, 3]:
            m.append(i + 1)</warning>