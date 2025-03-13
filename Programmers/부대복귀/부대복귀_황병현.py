from collections import deque

INF = int(1e9)

def solution(n, roads, sources, destination):
    answer = []
    graph = [[] for _ in range(n+1)]
    for a, b in roads:
        graph[a].append(b)
        graph[b].append(a)

    visited = [INF] * (n+1)
    q = deque()
    q.append(destination)
    visited[destination] = 0

    while q:
        now = q.popleft()
        for nxt in graph[now]:
            if visited[nxt] > visited[now] + 1:
                visited[nxt] = visited[now] + 1
                q.append(nxt)
                
    for start in sources:
        answer.append(visited[start] if visited[start] != INF else -1)

    return answer