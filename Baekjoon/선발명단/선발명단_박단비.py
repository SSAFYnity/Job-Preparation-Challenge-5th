def dfs(idx, total):
    global ans
    if idx == 11:
        ans = max(ans, total)
        return
    for i in range(11):
        if visited[i] == False and board[idx][i] > 0:
            visited[i] = True
            dfs(idx+1, total + board[idx][i])
            visited[i] = False

T = int(input())
for _ in range(T):
    board = [list(map(int, input().split())) for _ in range(11)]

    ans = 0
    visited = [False] * 11


    dfs(0, 0)
    print(ans)