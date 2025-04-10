import sys
input = sys.stdin.readline
N, S = map(int, input().split())
paint = [(0, 0)]
for _ in range(N):
    a, b = map(int, input().split())
    paint.append((a, b))

paint.sort(key=lambda x : (x[0], -x[1]))
dp = [0] * (N+1)
pre_max, pre_idx = 0, 0

for i in range(1, N+1):
    for j in range(pre_idx, i):
        if paint[i][0] - paint[j][0] < S:
            break
        pre_idx = j
        pre_max = max(pre_max, dp[j])
    dp[i] = max(pre_max + paint[i][1], dp[i])
print(max(dp))