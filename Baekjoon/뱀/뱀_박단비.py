from collections import deque

N = int(input())
K = int(input())
board = [[0] * (N+1) for _ in range(N+1)]
for _ in range(K): # 사과 위치 표시
    r, c = map(int, input().split())
    board[r][c] = 2
L = int(input())
dir_info = {}
for _ in range(L):
    X, C = input().split()
    dir_info[int(X)] = C

time = 0
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]
r, c = 1, 1 # 뱀 위치
board[r][c] = 1
d = 0 # 뱀의 이동방향
snake = deque()
snake.append((1, 1))

while True:
    nr, nc = r + dr[d], c + dc[d]
    # 뱀의 몸에 닿거나 벽에 닿으면 종료
    if nr <= 0 or nr > N or nc <= 0 or nc > N or (nr, nc) in snake:
        break
    # 사과가 없으면 꼬리가 위치한 칸 비우기
    if board[nr][nc] != 2:
        a, b = snake.popleft()
        board[a][b] = 0

    r, c = nr, nc
    board[r][c] = 1
    snake.append((r, c))
    time += 1

    # 해당 시간에 방향 전환 해야하는 경우
    if time in dir_info.keys():
        if dir_info[time] == 'D':
            d = (d + 1) % 4
        else:
            d = (d + 3) % 4

print(time+1)