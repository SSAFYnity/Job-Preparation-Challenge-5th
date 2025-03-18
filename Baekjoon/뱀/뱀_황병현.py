from collections import deque

n = int(input())
k = int(input())
apple = set()  # 사과 위치를 집합으로 관리
for _ in range(k):
    a, b = map(int, input().split())
    apple.add((a - 1, b - 1))

l = int(input())
point = deque([])  # 방향 전환 지점을 큐로 관리
for _ in range(l):
    point.append(list(input().split()))

# 뱀의 초기 위치와 방향 설정
snake = deque([(0, 0)])
dx = [0, 1, 0, -1]  # 동, 남, 서, 북
dy = [1, 0, -1, 0]
direction = 0  # 초기 방향은 동쪽

# 게임 진행
time = 0
while True:
    time += 1

    # 뱀의 머리 위치와 방향에 따라 이동 좌표 계산
    x, y = snake[-1]
    nx, ny = x + dx[direction], y + dy[direction]

    # 벽 또는 자신의 몸에 부딪히는 경우 게임 종료
    if nx < 0 or nx >= n or ny < 0 or ny >= n or (nx, ny) in snake:
        break

    # 이동한 좌표에 사과가 있는 경우
    if (nx, ny) in apple:
        apple.remove((nx, ny))  # 사과 제거
    else:
        snake.popleft()  # 사과가 없으면 꼬리를 이동

    # 뱀의 머리를 이동한 좌표로 추가
    snake.append((nx, ny))

    # 방향 전환 지점에 도달하면 방향 변경
    if point and time == int(point[0][0]):
        command = point.popleft()[1]
        if command == 'D':  # 오른쪽으로 90도 회전
            direction = (direction + 1) % 4
        else:  # 왼쪽으로 90도 회전
            direction = (direction - 1) % 4

print(time)
