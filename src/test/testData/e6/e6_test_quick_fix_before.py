def fors():
    x = []
    for i in range(100):
        x.append(i)

    x += []
    for i in range(100):
        x.append(i)

    x = [1, 2, 3]
    for i in range(100):
        x.append(i)

    for i in range(100):
        x.append(i)

    x = []
    for i in range(100):
        x.append(i * i + i / (i + 1))

    x = []
    for i in range(100):
        pass

    x = []

    for i in range(100):

        x.append(i)

    t = []
    for v, w in range(111):
        t.append(v)

    t = []
    for v, w in range(111):
        v.append(w)

    t = []
    for v in range(10):
        for w in range(10):
            t.append(v + w)

    print(x)