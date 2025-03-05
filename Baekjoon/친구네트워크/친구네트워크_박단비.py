import sys
input = sys.stdin.readline
def find(a):
    if parents[a] != a:
        parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    a = find(a)
    b = find(b)
    if a != b:
        parents[b] = a
        count[a] += count[b]
    print(count[a])

T = int(input())
for _ in range(T):
    F = int(input())
    idx = 0
    parents = {}
    count = {}
    for i in range(F):
        f1, f2 = input().split()
        if f1 not in parents:
            parents[f1] = f1
            count[f1] = 1
        if f2 not in parents:
            parents[f2] = f2
            count[f2] = 1
        union(f1, f2)