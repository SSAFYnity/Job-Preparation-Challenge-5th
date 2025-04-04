from sys import stdin
from heapq import heappop, heappush

def input():
    return stdin.readline().rstrip()

N, M = map(int,input().split())
lst = list(map(int,input().split()))

lst.sort()

h = []

for _ in range(M):
    try:
        time = lst.pop()
        heappush(h, time)
    except:
        continue
    
while lst:
    now = heappop(h)
    nxt = lst.pop()
    heappush(h, now+nxt)

print(max(h))