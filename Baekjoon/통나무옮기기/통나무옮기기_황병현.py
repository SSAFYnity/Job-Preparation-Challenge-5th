from sys import stdin
from collections import deque

def input():
    return stdin.readline().rstrip()

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def move_check(x,y,s):
    for i in [-1,1]:
        if s == 0:
            nx = x
            ny = y + i
        elif s == 1:
            nx = x + i
            ny = y
        if 0 <= nx < N and 0 <= ny < N and board[nx][ny] != "1": continue
        return False    
    return True

def rotation_check(x,y,s):
    for i in [-1,1]:
        for j in [-1,0,1]:
            if s == 0:
                nx = x + i
                ny = y + j
            elif s == 1:
                nx = x + j
                ny = y + i
            if 0 <= nx < N and 0 <= ny < N and board[nx][ny] != "1": continue
            return False
    return True

def bfs(wood, goal):
    visited = [[[-1]*2 for _ in range(N)] for _ in range(N)]
    q = deque()
    q.append(wood)
    visited[wood[0]][wood[1]][wood[2]] = 0
    
    while q:
        wx, wy, ws = q.popleft()

        if wx == goal[0] and wy == goal[1] and ws == goal[2]:
            return visited[goal[0]][goal[1]][goal[2]]
        
        for i in range(4):
            nx, ny = wx + dx[i], wy + dy[i]
            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny][ws] == -1 and board[nx][ny] != "1":
                if move_check(nx,ny,ws):
                    visited[nx][ny][ws] = visited[wx][wy][ws] + 1
                    q.append((nx,ny,ws))

        if visited[wx][wy][(ws+1)%2] == -1:
            if rotation_check(wx,wy,ws):
                visited[wx][wy][(ws+1)%2] = visited[wx][wy][ws] + 1
                q.append((wx,wy,(ws+1)%2))
    
    return 0

def get_status(array):
    if array[0][0] == array[1][0]:
        d = 0
    else:
        d = 1
    x = array[1][0]; y = array[1][1]
    return [x,y,d]

N = int(input())
board = [list(input()) for _ in range(N)]

wood = []
goal = []
for i in range(N):
    for j in range(N):
        if board[i][j] == "B":
            wood.append((i,j))
        elif board[i][j] == "E":
            goal.append((i,j))

wx,wy,ws = get_status(wood)
gx,gy,gs = get_status(goal)

print(bfs((wx,wy,ws), (gx,gy,gs)))