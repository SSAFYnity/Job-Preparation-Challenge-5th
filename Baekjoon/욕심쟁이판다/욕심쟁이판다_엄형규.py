import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def find(i, j):
    if visited[i][j] != -1:
        return visited[i][j]
    
    visited[i][j] = 1
    
    for di, dj in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
        ni, nj = i + di, j + dj
        if 0 <= ni < n and 0 <= nj < n and forest[i][j] < forest[ni][nj]:
            visited[i][j] = max(visited[i][j], 1 + find(ni, nj))
    
    return visited[i][j]

n = int(input())
forest = [list(map(int, input().split())) for _ in range(n)]
visited = [[-1] * n for _ in range(n)]

result = 0
for i in range(n):
    for j in range(n):
        result = max(result, find(i, j))

print(result)
