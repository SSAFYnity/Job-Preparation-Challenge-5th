from collections import deque
q = deque()
A, B, C = map(int, input().split())

water = [0, 0, C]
visited = []
q.append(water)
visited.append(water)
ans = []

while q:
    a, b, c = q.popleft()

    if a > 0:
        # a에서 b로 따르는 경우
        temp = (B - b)
        temp = min(a, temp)
        arr = [a - temp, b + temp, c]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)

        # a에서 c로 따르는 경우
        temp = (C - c)
        temp = min(a, temp)
        arr = [a - temp, b, c + temp]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)

    if b > 0:
        # b에서 a로 따르는 경우
        temp = (A - a)
        temp = min(b, temp)
        arr = [a + temp, b - temp, c]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)

        # b에서 c로 따르는 경우
        temp = (C - c)
        temp = min(b, temp)
        arr = [a, b - temp, c + temp]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)

    if c > 0:
        # c에서 a로 따르는 경우
        temp = (A - a)
        temp = min(c, temp)
        arr = [a + temp, b, c - temp]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)

        # c에서 b로 따르는 경우
        temp = (B - b)
        temp = min(c, temp)
        arr = [a, b + temp, c - temp]
        if arr not in visited:
            q.append(arr)
            visited.append(arr)
for v in visited:
    if v[0] == 0 and v[2] not in ans:
        ans.append(v[2])

ans.sort()
print(*ans)