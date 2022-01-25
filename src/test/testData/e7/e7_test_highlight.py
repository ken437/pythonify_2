thelist = []

def func1():
    for i in range(10):
        if i == 0:
            thelist.append(i)

def func2():


    for i in range(10):
        if i == 0:
            thelist.append(i)

def func3():
    iterthrough = [1, 2, 3, 4]

    list1 = []
    <warning>for i in iterthrough:
        if i % 2 == 0:
            list1.append(i)</warning>

    for i in iterthrough:
        if i % 3 == 0:
            list1.append(i)

    list2 = []
    for i in iterthrough:
        if i % 3 == 0:
            list2.append(i)
        else:
            print("Extra")

    list3 = []
    <warning>for i in iterthrough:
        if i % 3 == 0:
            # Comment
            list3.append(i)</warning>

    list4 = []
    <warning>for i in iterthrough:
        if i % 3 == 0:
            list4.append(i)</warning>
            # Comment

    list5 = []
    <warning>for i in iterthrough:
        # Comment
        if i % 3 == 0:
            list5.append(i)</warning>

    list6 = []
    <warning>for i in iterthrough:
        if i % 3 == 0:
            list6.append(i)</warning>
        # Comment

    list7 = []
    # Comment
    <warning>for i in iterthrough:
        if i % 3 == 0:
            list7.append(i)</warning>

    list8 = []


    <warning>for i in iterthrough:



        if i % 3 == 0:

            list8.append(i)</warning>

    list9 = [1]
    for i in iterthrough:
        if i % 3 == 0:
            list9.append(i * 8)

    list9 += []
    for i in iterthrough:
        if i % 3 == 0:
            list9.append(i * 8)

    list10 = []
    <warning>for i, k in [(1, 2), (3, 4), (5, 6)]:
        if i % 3 == 0:
            list10.append(i * 8)</warning>

    list11 = []
    for i in range(100):
        print(i)
        if i % 3 == 0:
            list11.append(10 + i)

    list12 = []

    for i in range(100):
        if i % 3 == 0:
            list12.append(i)
        print(i)

    list13 = []
    print("hi")
    for i in range(100):
        if i % 3 == 0:
            list13.append(i)

    list14 = []
    for i in range(100):
        if i % 3 == 0:
            print(i)
            list14.append(i)

    list15 = []
    for i in range(100):
        if i % 3 == 0:
            list15.append(i)
            print("hi")

    list16 = []
    for i in range(100):
        if i % 3 == 0:
            list10.append(i)

    list17 = []
    for i in range(100):
        if i % 3 == 0:
            list17.append(i)
        elif i % 2 == 0:
            list17.append(2 * i)

    list18 = []
    for i in range(100):
        if i % 3 == 0:
            list18.append(i)
        elif i % 2 == 0:
            list18.append(2 * i)
        else:
            print("Neither")