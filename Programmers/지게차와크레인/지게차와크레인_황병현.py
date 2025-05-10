from collections import deque

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def solution(storage, requests):
    answer = 0
    n = len(storage)
    m = len(storage[0])
    
    new_storage = [["0" for _ in range(m+2)]]
    for i in range(n):
        new_storage.append(["0"]+list(storage[i])+["0"])
    new_storage.append(["0" for _ in range(m+2)])
    
    for request in requests:
        if len(request) == 1:
            visited = [[False] * (m+2) for _ in range(n+2)]
            q = deque()
            visited[0][0] = True
            q.append((0,0))
            while q:
                x, y = q.popleft()
                for i in range(4):
                    nx, ny = x + dx[i], y + dy[i]
                    if 0 <= nx < n+2  and 0 <= ny < m+2 and not visited[nx][ny]:
                        if new_storage[nx][ny] == "0":
                            q.append((nx,ny))
                        if new_storage[nx][ny] == request:
                            new_storage[nx][ny] = "0"
                        visited[nx][ny] = True
                        
        else:
            for x in range(n+2):
                for y in range(m+2):
                    if new_storage[x][y] == request[0]:
                        new_storage[x][y] = "0"
    
    for i in range(n+2):
        for j in range(m+2):
            if new_storage[i][j] != "0":
                answer += 1
                
    return answer