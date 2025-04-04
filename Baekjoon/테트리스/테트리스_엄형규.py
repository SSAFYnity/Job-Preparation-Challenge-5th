import sys 
input = sys.stdin.readline

# 블록과 블록, 블록과 바닥 사이 채워져 있지 않은 칸이 생기면 안된다.
# 0, 90, 180, 270으로 회전 가능

blocks = [[] for _ in range(8)]
# 가로, 세로로
blocks[1].append([(0,0),(0,1),(0,2),(0,3)])
blocks[2].append([(0,0),(0,1),(1,0),(1,1)])
blocks[3].append([(0,0),(1,0),(1,1),(2,1)])
blocks[4].append([(0,0),(0,1),(1,1),(1,2)])
blocks[5].append([(0,0),(1,0),(2,0),(1,1)])
blocks[6].append([(0,0),(1,0),(2,0),(2,1)])
blocks[7].append([(0,0),(0,1),(1,0),(2,0)])

