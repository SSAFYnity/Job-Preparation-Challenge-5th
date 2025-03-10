def solution(board, skills):
    answer = 0
    N = len(board)
    M = len(board[0])
    new_board = [[0] * (M + 1) for _ in range(N + 1)]
    for skill in skills:
        type, r1, c1, r2, c2, n = skill
        if type == 1:
            n = -n
        new_board[r1][c1] += n
        new_board[r1][c2 + 1] += -n
        new_board[r2 + 1][c1] += -n
        new_board[r2 + 1][c2 + 1] += n
    for i in range(N):
        for j in range(1, M):
            new_board[i][j] += new_board[i][j - 1]

    for j in range(M):
        for i in range(1, N):
            new_board[i][j] += new_board[i - 1][j]

    for i in range(N):
        for j in range(M):
            board[i][j] += new_board[i][j]
            if board[i][j] >= 1:
                answer += 1
    return answer