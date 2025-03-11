import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline
N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]

# dp[i][j] : i, j행에서 최대로 갈 수 있는 칸 갯수
dp = [[0] * N for _ in range(N)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
answer = -1

def dfs(r, c):
    if dp[r][c]:
        return dp[r][c]
    dp[r][c] = 1

    for k in range(4):
        nr = r + dr[k]
        nc = c + dc[k]
        if 0 <= nr < N and 0 <= nc < N:
            if board[nr][nc] > board[r][c]:
                dp[r][c] = max(dp[r][c], dfs(nr, nc) + 1)

    return dp[r][c]
for i in range(N):
    for j in range(N):
        tmp = dfs(i, j)
        answer = max(tmp, answer)

print(answer)