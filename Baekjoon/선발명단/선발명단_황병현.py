from sys import stdin

def input():
    return stdin.readline().rstrip()

def solve(a, score):
    global max_score
    if a == 11:
        max_score = max(max_score, score)
        return
    
    for i in range(11):
        if visited[i] or not stat[a][i]: continue
        visited[i] = True
        solve(a+1, score + stat[a][i])
        visited[i] = False

N = int(input())
for i in range(N):
    stat = [list(map(int,input().split())) for _ in range(11)]
    visited = [False] * 11
    max_score = 0
    
    solve(0,0)
    print(max_score)
