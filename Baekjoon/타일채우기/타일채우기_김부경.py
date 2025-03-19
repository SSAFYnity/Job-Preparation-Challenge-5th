# dp
dp = [0] * 31
dp[0] = 1
dp[2] = 3
# 3*3+2
dp[4] = 11

for i in range(6, 31, 2):
    # 2칸 모양 붙이기 (3가지 경우)
    dp[i] = dp[i-2] * dp[2]
    # 중간에 = 인 특이 모양 붙이기 (|=|, |==| -> 4칸 부터 생김) (2가지 경우)
    for j in range(i-4, -1, -2):
        dp[i] += dp[j] * 2

n = int(input())
print(dp[n])
