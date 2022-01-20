def fors():
    x = [i for i in range(100)]

    x += []
    for i in range(100):
        x.append(i)

    x = [1, 2, 3]
    for i in range(100):
        x.append(i)

    for i in range(100):
        x.append(i)

    x = [i * i + i / (i + 1) for i in range(100)]

    x = []
    for i in range(100):
        pass

    x = [i for i in range(100)]

    t = [v for v, w in range(111)]

    t = []
    for v, w in range(111):
        v.append(w)

    t = []
    for v in range(10):
        for w in range(10):
            t.append(v + w)

    t = [v - 1 for v in [1, 2, 5, 6]]

    print(x)