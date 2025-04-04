import sys
import math
input = sys.stdin.readline

global N, M
# 콘센트에서 충전, 2**k 시간 필요, 콘센트 M
N, M = map(int, input().split())

charging_time = list(map(int, input().split()))
charging_time.sort(reverse=True)
total = [0]*M
no = 0
for i in range(len(charging_time)):
    total[no] += charging_time[i]
    if (no != 0 and total[no-1] == total[no]) or no == 0:
        no = (no + 1) % M
print(total[0])