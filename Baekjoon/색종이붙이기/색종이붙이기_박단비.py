board = [list(map(int, input().split())) for _ in range(10)]

paper = [5] * 5 # i * i 크기 색종이 몇개 남아있는지
answer = 26

# 색종이 붙일 수 있는지 확인(k*k 만큼 다 1인지 확인)
def check(i1, i2, j1, j2):
    for r in range(i1, i2+1):
        for c in range(j1, j2+1):
            if board[r][c] == 0:
                return False
    return True

# 색종이 붙이기
def attach(i1, i2, j1, j2):
    for r in range(i1, i2+1):
        for c in range(j1, j2+1):
            board[r][c] = 0

# 색종이 떼기
def remove(i1, i2, j1, j2):
    for r in range(i1, i2+1):
        for c in range(j1, j2+1):
            board[r][c] = 1

def func(cnt):
    global answer
    for r in range(10):
        for c in range(10):
            if board[r][c] == 1:
                for k in range(5):
                    nr = r + k
                    nc = c + k
                    # 경계 안 이고, 색종이가 남아있다면
                    if paper[k] > 0 and nr < 10 and nc < 10:
                        # 색종이 붙일 수 있는지 확인
                        if check(r, nr, c, nc):
                            attach(r, nr, c, nc)
                            paper[k] -= 1
                            func(cnt+1)
                            remove(r, nr, c, nc)
                            paper[k] += 1
                return
    answer = min(answer, cnt)

func(0)
if answer == 26:
    print(-1)
else:
    print(answer)

