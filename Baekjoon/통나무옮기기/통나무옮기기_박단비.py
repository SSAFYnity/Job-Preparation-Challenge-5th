import sys
from collections import deque
input = sys.stdin.readline

def checkDir(temp):
    if temp[0][1] == temp[1][1]:
        return 0 # 세로 방향 : 0
    return 1 # 가로 방향 : 1

def checkVisited(temp, d, cnt):
    if temp not in visited:
        visited.add(temp)
        q.append((temp, d, cnt+1))

def bfs():
    while q:
        tmp, d, cnt = q.popleft()

        if tmp == target:
            return cnt

        if d == 0: # 세로 뱡향일 경우
            for cmd in range(5):
                # U
                if cmd == 0:
                    new_tmp = deque(tmp)
                    r, c = new_tmp[0]
                    if (0 <= r-1 < N) and board[r-1][c] == '0':
                        new_tmp.pop()
                        new_tmp.appendleft((r-1, c))
                        checkVisited(tuple(new_tmp), 0, cnt)
                # D
                elif cmd == 1:
                    new_tmp = deque(tmp)
                    r, c = new_tmp[2]
                    if (0 <= r + 1 < N) and board[r+1][c] == '0':
                        new_tmp.popleft()
                        new_tmp.append((r+1, c))
                        checkVisited(tuple(new_tmp), 0, cnt)

                # L
                elif cmd == 2:
                    flag = 1
                    for r, c in tmp:
                        if not (0 <= c - 1 < N) or board[r][c-1] == '1':
                            flag = 0
                            break
                    if flag:
                        new_tmp = tuple([(r, c-1) for r, c in tmp])
                        checkVisited(new_tmp, 0, cnt)

                # R
                elif cmd == 3:
                    flag = 1
                    for r, c in tmp:
                        if not (0 <= c + 1 < N) or board[r][c + 1] == '1':
                            flag = 0
                            break
                    if flag:
                        new_tmp = tuple([(r, c + 1) for r, c in tmp])
                        checkVisited(new_tmp, 0, cnt)

                # T
                else:
                    flag = 1
                    for r, c in tmp:
                        if not (1 <= c < N-1):
                            flag = 0
                            break

                        if board[r][c-1] == '1' or board[r][c+1] == '1':
                            flag = 0
                            break
                    if flag:
                        cr, cc = tmp[1]
                        new_tmp = ((cr, cc-1), (cr, cc), (cr, cc+1))
                        checkVisited(new_tmp, 1, cnt)

        else: # 가로 방향일 경우
            for cmd in range(5):
                # U
                if cmd == 0:
                    flag = 1
                    for r, c in tmp:
                        if not (0 <= r - 1 < N) or board[r-1][c] == '1':
                            flag = 0
                            break
                    if flag:
                        new_tmp = tuple([(r-1, c) for r, c in tmp])
                        checkVisited(new_tmp, 1, cnt)

                # D
                elif cmd == 1:
                    flag = 1
                    for r, c in tmp:
                        if not (0 <= r + 1 < N) or board[r + 1][c] == '1':
                            flag = 0
                            break
                    if flag:
                        new_tmp = tuple([(r + 1, c) for r, c in tmp])
                        checkVisited(new_tmp, 1, cnt)

                # L
                elif cmd == 2:
                    new_tmp = deque(tmp)
                    r, c = new_tmp[0]
                    if (0 <= c-1 < N) and board[r][c-1] == '0':
                        new_tmp.pop()
                        new_tmp.appendleft((r, c-1))
                        checkVisited(tuple(new_tmp), 1, cnt)

                # R
                elif cmd == 3:
                    new_tmp = deque(tmp)
                    r, c = new_tmp[2]
                    if (0 <= c + 1 < N) and board[r][c + 1] == '0':
                        new_tmp.popleft()
                        new_tmp.append((r, c + 1))
                        checkVisited(tuple(new_tmp), 1, cnt)
                # T
                else:
                    flag = 1
                    for r, c in tmp:
                        if not (1 <= r < N-1):
                            flag = 0
                            break

                        if board[r-1][c] == '1' or board[r+1][c] == '1':
                            flag = 0
                            break

                    if flag:
                        cr, cc = tmp[1]
                        new_tmp = ((cr-1, cc), (cr, cc), (cr+1, cc))
                        checkVisited(new_tmp, 0, cnt)

    return 0

N = int(input())
board = []
tong = []
target = []
for i in range(N):
    arr = list(input().rstrip())
    for j in range(N):
        if arr[j] == 'B':
            tong.append((i, j))
            arr[j] = '0'
        elif arr[j] == 'E':
            target.append((i, j))
            arr[j] = '0'
    board.append(arr)

startDir = checkDir(tong)
target = tuple(target)

visited = set()
visited.add(tuple(tong))
q = deque()
q.append((tong, startDir, 0))

print(bfs())