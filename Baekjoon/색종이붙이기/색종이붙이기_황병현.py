from sys import stdin

def input():
    return stdin.readline().rstrip()

def check(x1, y1, x2, y2):
    for i in range(x1, x2+1):
        for j in range(y1, y2+1):
            if not paper[i][j]:
                return False
            
    return True

def attach(x1, y1, x2, y2, w):
    for i in range(x1, x2+1):
        for j in range(y1, y2+1):
            paper[i][j] = w

def solve(cnt):
    global result
    for x in range(10):
        for y in range(10):
            if paper[x][y]:
                for i in range(5):
                    nx, ny = x + i, y + i
                    if paper_lst[i] and nx < 10 and ny < 10:
                        if check(x,y,nx,ny):
                            attach(x,y,nx,ny,0)
                            paper_lst[i] -= 1
                            solve(cnt + 1)
                            paper_lst[i] += 1
                            attach(x,y,nx,ny,1)
                return
        
    result = min(result, cnt)

paper = [list(map(int,input().split())) for _ in range(10)]

paper_lst = [5,5,5,5,5]
result = 26

solve(0)

print(result if result != 26 else -1)