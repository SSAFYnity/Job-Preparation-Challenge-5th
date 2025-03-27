import sys, heapq
input = sys.stdin.readline

def dijkstra():
    visited = [50000001]*(n+1)
    heap = [(0, 1)]
    visited[1] = 0

    while heap:
        cows, curr = heapq.heappop(heap)

        if visited[curr] < cows:
            continue

        for next, cow in graph[curr]:
            ssum_cows = visited[curr] + cow
            if ssum_cows < visited[next]:
                visited[next] = ssum_cows
                heapq.heappush(heap, (ssum_cows, next))
    return visited[n]

# 최소 한의 소 만나기(최단경로)
# 1 -> N 으로 이동
n, m = map(int, input().split())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    # a-b사이에 c마리의 소 존재
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))
print(dijkstra())
