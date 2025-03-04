# destination에서 갈 수 있는 모든 노드를 검사
from collections import deque


def solution(n, roads, sources, destination):
    answer = []
    graph = [[] for _ in range(n + 1)]
    for road in roads:
        graph[road[0]].append(road[1])
        graph[road[1]].append(road[0])

    visited = [-1] * (n + 1)
    visited[destination] = 0
    q = deque()
    q.append(destination)
    while q:
        node = q.popleft()

        for next_node in graph[node]:
            if visited[next_node] == -1:
                q.append(next_node)
                visited[next_node] = visited[node] + 1
    for source in sources:
        answer.append(visited[source])
    return answer