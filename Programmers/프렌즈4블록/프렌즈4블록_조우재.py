directions = [(1, 0), (0, 1), (1, 1)]


def init_board(board):
    for i in range(len(board)):
        board[i] = list(board[i])


def search_block(board):
    target = []
    candidate = []

    for y in range(len(board) - 1):
        for x in range(len(board[0]) - 1):
            block = board[y][x]
            if block == "*":
                continue
            candidate.append((y, x))

            for dy, dx in directions:
                my, mx = y + dy, x + dx
                if board[my][mx] == block:
                    candidate.append((my, mx))
            if len(candidate) == 4:
                target += candidate
            candidate = []

    return target


def remove_block(board, target):
    for y, x in target:
        board[y][x] = "*"


def down_block(board):
    stack = []
    for x in range(len(board[0])):
        for y in range(len(board)):
            block = board[y][x]
            if block != "*":
                stack.append(block)
        for y in range(len(board) - 1, -1, -1):
            board[y][x] = stack.pop() if stack else "*"


def count_space(board):
    return sum([1 for y in range(len(board)) for x in range(len(board[0])) if board[y][x] == "*"])


def solution(m, n, board):
    init_board(board)

    while True:
        target = search_block(board)
        if not target:
            break
        remove_block(board, target)
        down_block(board)

    return count_space(board)
