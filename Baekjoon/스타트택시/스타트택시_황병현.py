from sys import stdin
from collections import deque
from heapq import heappush, heappop

def input():
    return stdin.readline().rstrip()

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def move(start_x, start_y, goal_x, goal_y, oil):
    q = deque()
    visited = [[False] * N for _ in range(N)]
    q.append((start_x, start_y, 0))
    visited[start_x][start_y] = True

    while q:
        x, y, dist = q.popleft()

        if x == goal_x and y == goal_y:
            return goal_x, goal_y, oil - dist + (dist*2)

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and board[nx][ny] != 1 and dist+1 <= oil:
                visited[nx][ny] = True
                q.append((nx, ny, dist+1))

    return 0, 0, -1
    
def find(start_x, start_y, oil):
    h = []
    heappush(h,(0, start_x, start_y))
    visited = [[False] * N for _ in range(N)]
    visited[start_x][start_y] = True

    while h:
        dist, x, y = heappop(h)

        if board[x][y] >= 2:
            num = board[x][y]
            board[x][y] = 0
            return x, y, oil - dist, num
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and board[nx][ny] != 1 and dist+1 <= oil:
                visited[nx][ny] = True
                heappush(h, (dist+1, nx, ny))
    
    return 0, 0, 0, -1


N, M, F = map(int,input().split())
board = [list(map(int,input().split())) for _ in range(N)]

x, y = map(int,input().split())
x, y = x-1, y-1
people = [0,0]

for i in range(M):
    p_x, p_y, g_x, g_y = map(int,input().split())
    board[p_x-1][p_y-1] = i + 2
    people.append((g_x-1, g_y-1))

check = True
for _ in range(M):
    find_x, find_y, F, num = find(x, y, F)
    if F == -1:
        print(-1)
        check = False
        break
    goal = people[num]
    x, y, F = move(find_x, find_y, goal[0], goal[1], F)
    if F == -1:
        print(-1)
        check = False
        break

if check:
    print(F)