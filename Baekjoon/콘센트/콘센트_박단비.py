from collections import deque
N, M = map(int, input().split())
time = list(map(int, input().split()))
time.sort(reverse=True)
time = deque(time)

ans = 0
concent = [0] * M
while True:
    min_v = 1e9
    for i in range(M):
        if concent[i] == 0 and time:
            concent[i] = time.popleft()
        min_v = min(min_v, concent[i])

    for i in range(M):
        concent[i] -= min_v
    ans += min_v

    if min_v == 0 or not time:
        ans += max(concent)
        break

print(ans)
