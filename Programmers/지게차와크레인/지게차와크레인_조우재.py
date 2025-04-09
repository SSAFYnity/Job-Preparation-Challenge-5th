from collections import deque


def search(n, m, grid, target):
    dq = deque([(0, 0)])
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    visited = [[False] * m for _ in range(n)]
    visited[0][0] = True
    check = []

    while dq:
        y, x = dq.popleft()
        for dy, dx in directions:
            my, mx = y + dy, x + dx
            if 0 <= my < n and 0 <= mx < m and not visited[my][mx] and (grid[my][mx] == "0" or grid[my][mx] == target):
                if grid[my][mx] == "0":
                    dq.append((my, mx))
                else:
                    check.append((my, mx))
                visited[my][mx] = True

    for y, x in check:
        grid[y][x] = "0"


def solution(storage, requests):
    answer = 0
    grid = [["0"] * (len(storage[0]) + 2)] + [["0"] + list(i) + ["0"] for i in storage] + [
        ["0"] * (len(storage[0]) + 2)]
    n = len(grid)
    m = len(grid[0])

    for request in requests:
        if len(request) == 1:
            search(n, m, grid, request)
        else:
            for i in range(1, n):
                for j in range(1, m):
                    if grid[i][j] in request:
                        grid[i][j] = "0"

    for i in range(1, n):
        for j in range(1, m):
            if grid[i][j] != "0":
                answer += 1

    return answer
