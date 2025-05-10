def solution(m, n, board):
    # 블록 제거하는 함수
    def remove_blocks():
        # 제거될 블록 위치 저장
        target_blocks = set()
        for row in range(m-1):
            for col in range(n-1):
                # 블록이 있으면 조건 탐색
                if board[row][col] == "":
                    continue
                # 4개의 블록 모양이 같으면 사라질 블럭
                if board[row][col] == board[row][col+1] == board[row+1][col] == board[row+1][col+1]:
                    target_blocks |= {(row,col), (row,col+1), (row+1,col), (row+1,col+1)}
        return target_blocks

    # 블록 내리는 함수
    def drop_blocks():
        # 열 기준 처리
        for col in range(n):
            # 현재 열의 블록 모양을 저장할 스택
            stack = []
            # 아래에서 위로 올라가며 블록만 찾아 스택에 추가
            for row in range(m-1,-1,-1):
                if board[row][col] != "":
                    stack.append(board[row][col])
                    board[row][col] = ""
            # 블록 개수만큼 위쪽부터 블록모양 채워 넣기
            for row in range(m - len(stack), m):
                board[row][col] = stack.pop()

    # 지워질 블록 수 저장
    answer = 0
    # list로 변경
    for i in range(m):
        board[i] = list(board[i])
    # 지워질 블록이 존재하면 계속 탐색
    while True:
        # 현재 타임에 사라지는 블록 저장
        removable_blocks = remove_blocks()
        # 지워지는 블록이 없으면 탐색 종료
        if not removable_blocks:
            break
        # 사라지는 블록 개수 추가
        answer += len(removable_blocks)
        # 블록 제거하기
        for i, j in removable_blocks:
            board[i][j] = ""
        # 블록 내리기
        drop_blocks()
    return answer
