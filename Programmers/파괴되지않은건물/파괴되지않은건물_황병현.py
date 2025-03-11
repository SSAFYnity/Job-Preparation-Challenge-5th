def solution(board, skill):
    answer = 0
    N, M = len(board), len(board[0])
    
    lst = [[0] * (M + 1) for _ in range(N + 1)]
    
    for t, x1, y1, x2, y2, degree in skill:
        if t == 1:
            degree = -degree
            
        lst[x1][y1] += degree
        lst[x1][y2+1] += -degree
        lst[x2+1][y1] += -degree
        lst[x2+1][y2+1] += degree
        
    for y in range(M):
        for x in range(1, N):
            lst[x][y] += lst[x-1][y]
            
    for x in range(N):
        for y in range(1, M):
            lst[x][y] += lst[x][y-1]
            
    for x in range(N):
        for y in range(M):
            if board[x][y] + lst[x][y] > 0:
                answer += 1
            
    return answer