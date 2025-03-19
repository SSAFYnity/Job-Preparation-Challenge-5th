from sys import stdin

def input():
    return stdin.readline().rstrip()

N = int(input())

dp = [0] * 31
dp[0], dp[2] = 1, 3

for i in range(4, N+1, 2):
    dp[i] = dp[i-2] * 3
    for j in range(i-4, -1, -2):
        dp[i] += dp[j] * 2
        
print(dp[N])
