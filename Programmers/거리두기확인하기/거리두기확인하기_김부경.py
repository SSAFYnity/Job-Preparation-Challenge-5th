from collections import deque

def solution(places):
    # 해당 응시자가 거리두기를 지키고 있는 지 확인 
    def bfs(r, x, y):
        nonlocal visited
        queue = deque()
        queue.append((x, y))
        visited[x][y] = 0
        while queue:
            x, y = queue.popleft()
            # 다른 응시자 만나면 False 반환
            if visited[x][y] and places[r][x][y] == "P":
                return False
            # 맨해튼 거리 2이면 탐색 안함
            if visited[x][y] == 2:
                continue
            # 4방향 탐색
            for k in range(4):
                nx = x + dx[k]
                ny = y + dy[k]
                # 파티션이 아니면 탐색
                if 0 <= nx < 5 and 0 <= ny < 5 and visited[nx][ny] == -1 and places[r][x][y] != "X":
                    visited[nx][ny] = visited[x][y] + 1
                    queue.append((nx, ny))
        return True
        
    # 해당 대기실이 거리두기가 유지되는 지 확인
    def is_distanced(wr):
        # 해당 대기실 탐색
        for i in range(5):
            for j in range(5):
                # 응시자를 만나면 거리두기 확인
                if places[wr][i][j] == "P":
                    # 거리두기를 지키지 않고 있으면 0 반환
                    if not bfs(wr, i, j):
                        return 0
        # 거리두기를 지키고 있으면 1 반환
        return 1
        
        
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    answer = []
    # 5개의 대기실 탐색
    for room in range(5):
        # 각 대기실의 자리 방문체크
        visited = [[-1]*5 for _ in range(5)]
        # 거리두기 결과 담기
        answer.append(is_distanced(room))

    return answer
