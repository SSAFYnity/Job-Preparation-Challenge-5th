from collections import defaultdict, deque


def search(n, destination, graph):
    dq = deque([destination])
    visited = {destination}
    distance = [-1] * (n + 1)
    distance[destination] = 0

    while dq:
        current_region = dq.popleft()

        for nxt_region in graph[current_region]:
            if nxt_region not in visited:
                dq.append(nxt_region)
                visited.add(nxt_region)
                distance[nxt_region] = distance[current_region] + 1

    return distance


def solution(n, roads, sources, destination):
    graph = defaultdict(list)
    for s, e in roads:
        graph[s].append(e)
        graph[e].append(s)

    distance = search(n, destination, graph)

    return [distance[i] for i in sources]