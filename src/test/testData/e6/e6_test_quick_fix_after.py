def fors():
    x = [i for i in range(100)]

    for i in range(100):
        x.append(i)

    x = []
    for i in range(100):
        x.append(i * i + i / (i + 1))

    x = []
    for i in range(100):
        pass

    x = [i for i in range(100)]

    print(x)