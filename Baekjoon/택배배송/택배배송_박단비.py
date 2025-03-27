# 다익스트라
from heapq import heappush, heappop
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

# 1 -> N까지 가는 최소 비용
dist = [1e9] * (N+1)


def dijkstra(n):
    heap = []
    dist[n] = 0

    heappush(heap, [0, n])

    while heap:
        cur_cost, cur_v = heappop(heap)

        if cur_cost > dist[cur_v]:
            continue

        for next_v, next_cost in graph[cur_v]:
            sum_cost = cur_cost + next_cost
            if sum_cost < dist[next_v]:
                dist[next_v] = sum_cost
                heappush(heap, [dist[next_v], next_v])
dijkstra(1)
print(dist[N])

