from collections import deque
import sys
input = sys.stdin.readline

N, M, oil = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
taxi_r, taxi_c = map(int, input().split())
people = [list(map(int, input().split())) for _ in range(M)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def bfs(s_r, s_c):
    q = deque()
    q.append((s_r, s_c))
    visited = [[-1] * N for _ in range(N)]
    visited[s_r][s_c] = 0
    while q:
        r, c = q.popleft()
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < N and 0 <= nc < N:
                if visited[nr][nc] == -1 and board[nr][nc] == 0:
                    q.append((nr, nc))
                    visited[nr][nc] = visited[r][c] + 1
    return visited

def check_dist(visited, people):
    for i in range(len(people)):
        s_r, s_c, e_r, e_c = people[i]
        people[i].append(visited[s_r-1][s_c-1])
    # 내림차순으로 해서 뒤에서부터 하나씩 꺼낼 수 있게
    people.sort(key=lambda x : (-x[4], -x[0], -x[1]))

while people:
    # 현재 택시 위치 기준 가장 가까운 승객 찾기
    visited = bfs(taxi_r-1, taxi_c-1)
    check_dist(visited, people)
    s_r, s_c, e_r, e_c, dist = people.pop()

    # 선택되지 못한 승객들의 택시 위치로부터의 거리 없애기
    for peo in people:
        peo.pop()

    visited = bfs(s_r-1, s_c-1)
    dist2 = visited[e_r-1][e_c-1]
    taxi_r, taxi_c = e_r, e_c

    if dist == -1 or dist2 == -1:
        print(-1)
        exit()
    # 택시에서 가장 가까운 승객으로 가는 기름 양 감소
    oil -= dist
    if oil < 0:
        print(-1)
        exit()

    # 승객 태우고 승객 목적지까지 이동하는 기름 양 감소
    oil -= dist2
    if oil < 0:
        print(-1)
        exit()

    # 도착지에 도착해서 이동 거리 2배 연료 주입
    oil += dist2 * 2

if oil < 0:
    print(-1)

else:
    print(oil)