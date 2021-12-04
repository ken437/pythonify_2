def func1():
    a = []
    b = {}
    b = <warning>dict()</warning>
    b = dict(1)
    b = [<warning>dict()</warning> for _ in range(10)]