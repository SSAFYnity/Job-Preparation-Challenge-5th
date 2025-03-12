# 아이디어
# 2차원 배열 만들어서 테두리는 1, 사각형 내부는 0으로 채움. BFS로 최단거리 찾기
# 테두리가 붙어있는 경우에 잘못된 경로로 갈 수 있어서 2배씩 늘리기
from collections import deque


def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    board = [[-1] * 102 for _ in range(102)]
    visited = [[-1] * 102 for _ in range(102)]
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]

    # 테투리 표시하기
    for r in rectangle:
        x1, y1, x2, y2 = map(lambda x: x * 2, r)
        for i in range(x1, x2 + 1):
            for j in range(y1, y2 + 1):
                if x1 < i < x2 and y1 < j < y2:
                    board[i][j] = 0
                elif board[i][j] != 0:
                    board[i][j] = 1
    # 2배로 늘렸으니까 좌표도 2배씩 늘려주기
    cr, cc, ir, ic = 2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY
    q = deque()
    q.append((cr, cc))
    visited[cr][cc] = 1
    while q:
        r, c = q.popleft()
        if r == ir and c == ic:
            answer = visited[r][c] // 2
            break
        for k in range(4):
            nr = r + dr[k]
            nc = c + dc[k]
            if 0 <= nr < 102 and 0 <= nc < 102:
                if board[nr][nc] == 1 and visited[nr][nc] == -1:
                    q.append((nr, nc))
                    visited[nr][nc] = visited[r][c] + 1

    return answer