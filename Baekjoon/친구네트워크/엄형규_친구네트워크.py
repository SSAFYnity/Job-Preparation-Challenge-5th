import sys
input = sys.stdin.readline

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)

    if a != b:
        parent[b] = a
        visited[a] += visited[b]
    print(visited[a])

T = int(input())
for t in range(T):
    num = int(input())
    parent = {}
    visited = {}
    for i in range(num):
        a, b = input().split()
        if a not in parent:
            parent[a] = a
            visited[a] = 1
        if b not in parent:
            parent[b] =b
            visited[b] = 1
        union(a, b)