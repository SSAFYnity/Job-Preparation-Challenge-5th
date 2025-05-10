from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):   
    board = [[False] * 101 for _ in range(101)]
    visited = [[False] * 101 for _ in range(101)]
    
    for x1, y1, x2, y2 in rectangle:
        for x in range(x1*2, x2*2+1):
            for y in range(y1*2, y2*2+1):
                board[x][y] = True
                
    for x1, y1, x2, y2 in rectangle:
        for x in range(x1*2+1, x2*2):
            for y in range(y1*2+1, y2*2):
                board[x][y] = False
                
    q = deque()
    q.append((characterX*2, characterY*2, 0))
    visited[characterX*2][characterY*2] = True
    
    dx = [1,-1,0,0]
    dy = [0,0,1,-1]
    
    while q:
        x, y, dist = q.popleft()
        if x == itemX*2 and y == itemY*2:
            return dist//2
            
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < 101 and 0 <= ny < 101 and not visited[nx][ny] and board[nx][ny]:
                visited[nx][ny] = True
                q.append((nx, ny, dist + 1))
    