from collections import deque

def solution(places):
    answer = []

    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    def bfs(i, j, place):
        q = deque()
        q.append((i, j))
        visited = [[-1] * 5 for _ in range(5)]
        visited[i][j] = 0
        while q:
            r, c = q.popleft()
            for k in range(4):
                nr = r + dr[k]
                nc = c + dc[k]
                if 0 <= nr < 5 and 0 <= nc < 5:
                    if place[nr][nc] != 'X' and visited[nr][nc] == -1:
                        if place[nr][nc] == 'P' and visited[r][c] + 1 <= 2:
                            return False
                        q.append((nr, nc))
                        visited[nr][nc] = visited[r][c] + 1
        return True

    for place in places:
        possible = True
        for i in range(5):
            for j in range(5):
                if place[i][j] == 'P':
                    result = bfs(i, j, place)
                    if not result:
                        answer.append(0)
                        possible = False
                        break
            if not possible:
                break
        if possible:
            answer.append(1)

    return answer