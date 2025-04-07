def check(board, key, M, N, r, c):
    answer = True
    for i in range(M):
        for j in range(M):
            board[r + i][c + j] += key[i][j]

    for i in range(N):
        for j in range(N):
            if board[M+i][M+j] != 1:
                answer = False
                break
        if not answer:
            break

    for i in range(M):
        for j in range(M):
            board[r + i][c + j] -= key[i][j]
    return answer

def rotate(key):
    n = len(key)
    rotate_key = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            rotate_key[j][n-1-i] = key[i][j]
    return rotate_key

def solution(key, lock):
    N = len(lock)
    M = len(key)
    board = [[0] * (N + 2 * M) for _ in range(N + 2 * M)]
    for i in range(N):
        for j in range(N):
            board[M+i][M+j] = lock[i][j]

    for k in range(4):
        key = rotate(key)
        for i in range(1, M+N):
            for j in range(1, M+N):
                if check(board, key, M, N, i, j):
                    return True
    return False