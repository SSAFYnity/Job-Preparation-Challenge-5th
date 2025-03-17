# 뱀은 사과 먹으면 길이 1 길어진다.
# 머리가 몸에 다이면 끝
# 사과먹으면 사과 자리에 머리가 생긴다.
# 사과 없으면 사과 자리에 머리 넣고 꼬리 1짧아진다.
# 보드 크기 n
from collections import defaultdict,deque
n = int(input())
# 사과의 개수 k
k = int(input())
# 사과의 위치
graph = [[0]*n for _ in range(n)]
for _ in range(k):
    j, i = map(int, input().split())
    graph[j-1][i-1] = 1
# 시작 위치인 0,0에는 사과가 없다
head = [(0,0)]
tail = deque()
# 방향 변환 정보
l = int(input())
direction = defaultdict(int)
for _ in range(l):
    t, where = input().split()
    t = int(t)
    direction[t] = where
rotate = [(1,0),(0,1),(-1,0),(0,-1)] # 오른쪽(D)이면 1, 왼족(L)이면 -1
cnt = 0
time = 0
while True:
    x, y = head.pop()
    graph[y][x] = 2
    tail.append((x,y))
    if direction[time] == 'D':
        cnt += 1
        cnt %= 4
    elif direction[time] == 'L':
        cnt -= 1
        cnt %= 4
    dx,dy = rotate[cnt]
    nx = dx + x
    ny = dy + y
    if nx>= n or ny>= n or nx<0 or ny<0:
        break
    if graph[ny][nx] == 1:# 사과가 있다면
        head.append((nx,ny))
        time += 1
    elif graph[ny][nx] == 0: # 사과가 없다면
        head.append((nx,ny))
        gx,gy = tail.popleft() # 몸 길이가 하나 짧아진다.
        graph[gy][gx] = 0
        time += 1
    if (nx,ny) in tail:
        break
print(time+1)