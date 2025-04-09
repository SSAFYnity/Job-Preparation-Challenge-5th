from collections import deque

def solution(storage, requests):
    n = len(storage)
    m = len(storage[0])
    answer = n * m
    board = [[0] * (m + 2) for _ in range(n + 2)]
    for i in range(n):
        for j in range(m):
            board[i + 1][j + 1] = storage[i][j]

    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    def bfs(q, visited, t):
        global answer
        tmp = 0
        N = len(visited)
        M = len(visited[0])
        while q:
            r, c = q.popleft()

            for k in range(4):
                nr = r + dr[k]
                nc = c + dc[k]
                if 0 <= nr < N and 0 <= nc < M and visited[nr][nc] == 0:
                    if board[nr][nc] == 0:
                        q.append((nr, nc))
                        visited[nr][nc] = 1
                    elif board[nr][nc] == t:
                        tmp += 1
                        board[nr][nc] = 0
                        visited[nr][nc] = 1
        return tmp

    for request in requests:
        target = request[0]

        if len(request) == 2:
            for i in range(n + 2):
                for j in range(m + 2):
                    if board[i][j] == target:
                        board[i][j] = 0
                        answer -= 1

        else:
            q = deque()
            visited = [[0] * (m+2) for _ in range(n+2)]
            for j in range(m + 2):
                q.append((0, j))
                visited[0][j] = 1
                q.append((n+1, j))
                visited[n+1][j] = 1

            for i in range(n):
                q.append((i+1, 0))
                visited[i+1][0] = 1
                q.append((i, m+1))
                visited[i+1][m+1] = 1
            answer -= bfs(q, visited, target)

    return answer