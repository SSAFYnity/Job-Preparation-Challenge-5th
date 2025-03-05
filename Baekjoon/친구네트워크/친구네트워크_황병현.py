from sys import stdin

def input():
    return stdin.readline().rstrip()

def find(x):
    if network[x] != x:
        network[x] = find(network[x])
        return network[x]

    return x

def union(x, y):
    p_x = find(x)
    p_y = find(y)

    if p_x != p_y:
        network[p_y] = p_x
        num[p_x] += num[p_y]

T = int(input())
for _ in range(T):
    F = int(input())

    network = dict()
    num = dict()

    for _ in range(F):
        friend1, friend2 = map(str, input().split())

        if friend1 not in network:
            network[friend1] = friend1
            num[friend1] = 1

        if friend2 not in network:
            network[friend2] = friend2
            num[friend2] = 1

        union(friend1, friend2)

        print(num[find(friend1)])