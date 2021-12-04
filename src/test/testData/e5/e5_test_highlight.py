def func1():
    a = []
    b = {}
    <warning>b = dict()</warning>
    b = dict(1)
    b = [<warning>dict()</warning> for _ in range(10)]