import sys

sys.setrecursionlimit(500**2)

def input():
    return sys.stdin.readline().rstrip()

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def solve(x,y):
    if visited[x][y] != -1: return visited[x][y]

    visited[x][y] = 1

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < N and 0 <= ny < N and board[nx][ny] > board[x][y]:
            check = 1
            check += solve(nx, ny)

            visited[x][y] = max(check, visited[x][y])

    return visited[x][y]

N = int(input())
board = [list(map(int,input().split())) for _ in range(N)]
visited = [[-1] * N for _ in range(N)]
result = 0

for x in range(N):
    for y in range(N):
        if visited[x][y] == -1:
            result = max(result, solve(x, y))

print(result)