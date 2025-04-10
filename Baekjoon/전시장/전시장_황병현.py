from sys import stdin

def input():
    return stdin.readline().rstrip()

def binary_search(num):
    result = 0
    l, r = 0, len(height)
    while l <= r:
        mid = (l+r) // 2
        if height[mid] <= num:
            result = max(result, price[height[mid]])
            l = mid + 1
        elif height[mid] > num:
            r = mid - 1

    return result

N, S = map(int, input().split())
height = []
price = {}
for _ in range(N):
    h, c = map(int,input().split())
    if h < S: continue
    elif h in price:
        price[h] = max(price[h], c)
    else:
        price[h] = c
        height.append(h)

height.sort()

dp = [0] * len(height)

for idx, h in enumerate(height):
    if idx == 0: 
        dp[0] = price[height[0]]
        continue
    dp[idx] = max(dp[idx-1], binary_search(h-S) + price[h])
    price[h] = dp[idx]

print(dp[-1])
