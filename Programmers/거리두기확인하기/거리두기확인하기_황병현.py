dx = [0,0,1,-1]
dy = [1,-1,0,0]                

def solution(places):
    answer = []
    
    def find(x, y, cnt, place):
        result = True
        
        if cnt == 2:
            return result

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < 5 and 0 <= ny < 5 and not visited[nx][ny]:
                visited[nx][ny] = True
                if place[nx][ny] == "O":
                    result = find(nx,ny, cnt + 1, place)
                    if not result:
                        break
                elif place[nx][ny] == "P":
                    return False
                
        return result
    
    for place in places:
        check = True
        for i in range(5):
            for j in range(5):
                if place[i][j] == "P":
                    visited = [[False] * 5 for _ in range(5)]
                    visited[i][j] = True
                    check = find(i,j,0,place)
                    if not check:
                        break
            if not check:
                break
        if not check:
            answer.append(0)
        else:
            answer.append(1)
                        
    return answer