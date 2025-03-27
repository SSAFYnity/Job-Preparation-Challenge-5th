from sys import stdin
import heapq

def input():
    return stdin.readline().rstrip()

def dijkstra():
    dist = [float('inf') for _ in range(N+1)]
    dist[1] = 0
    q = []
    heapq.heappush(q, (0, 1))
    
    while q:
        weight, now = heapq.heappop(q)
        if dist[now] < weight: continue
        
        for i in graph[now]:
            cost = weight + i[1]
            if cost < dist[i[0]]:
                dist[i[0]] = cost
                heapq.heappush(q,(cost, i[0]))
    
    return dist[N]
        
N, M = map(int,input().split())

graph = [[] for _ in range(N+1)]

for _ in range(M):
    u, v, w = map(int,input().split())
    graph[u].append((v, w))
    graph[v].append((u, w))
    
print(dijkstra())