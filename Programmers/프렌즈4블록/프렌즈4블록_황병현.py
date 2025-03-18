def solution(m, n, board):
    answer = 0
    
    while True:
        check = False
        visited = [[0] * n for _ in range(m)]
        
        for i in range(m-1):
            for j in range(n-1):
                if board[i][j] != '' and board[i][j] == board[i+1][j] == board[i][j+1] == board[i+1][j+1]:
                    visited[i][j], visited[i+1][j], visited[i][j+1], visited[i+1][j+1] = 1, 1, 1, 1
                    check = True
                    
        if not check:
            break
        
        new_board = [[''] * n for _ in range(m)]
        
        for i in range(m-1,-1,-1):
            for j in range(n):
                if visited[i][j] == 0:
                    new_board[i][j] = board[i][j]
                else:
                    if visited[i][j] == 1: 
                        answer += 1
                    idx = i - 1
                    while idx >= 0:
                        if visited[idx][j] == 0:
                            visited[idx][j] = 2
                            new_board[i][j] = board[idx][j]
                            break
                        idx -= 1

        board = new_board

    return answer