def solution(m, n, board):
    answer = 0
    board = [list(x) for x in board]

    tmp = set()
    while True:
        for i in range(m - 1):
            for j in range(n - 1):
                target = board[i][j]
                if target != 0:
                    if (board[i][j + 1] == target and board[i + 1][j] == target and board[i + 1][j + 1] == target):
                        tmp.add((i, j))
                        tmp.add((i, j + 1))
                        tmp.add((i + 1, j))
                        tmp.add((i + 1, j + 1))

        if tmp:
            answer += len(tmp)
            for r, c in tmp:
                board[r][c] = 0
            tmp = set()
        else:
            break

        for j in range(n):
            for i in range(m):
                for ii in range(1, m - i):
                    if board[ii][j] == 0:
                        board[ii - 1][j], board[ii][j] = board[ii][j], board[ii - 1][j]

    return answer