def solution(board, skill):
    answer = 0
    n, m = len(board), len(board[0])
    diff = [[0] * (m + 1) for _ in range(n + 1)]

    for types, r1, c1, r2, c2, degree in skill:
        change = degree if types == 2 else -degree
        diff[r1][c1] += change
        diff[r1][c2 + 1] -= change
        diff[r2 + 1][c1] -= change
        diff[r2 + 1][c2 + 1] += change

    for y in range(n):
        for x in range(1, m):
            diff[y][x] += diff[y][x - 1]

    for x in range(m):
        for y in range(1, n):
            diff[y][x] += diff[y - 1][x]

    for y in range(n):
        for x in range(m):
            board[y][x] += diff[y][x]
            if board[y][x] > 0:
                answer += 1

    return answer
