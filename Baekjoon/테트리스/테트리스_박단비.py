# 모든 블럭의 경우를 모두 나눠서 일일이 구현
C, P = map(int, input().split())
height = list(map(int, input().split()))
ans = 0

if P == 1:
    #ㅣ 모양 블럭은 모든 열에 놓을 수 있음
    ans += C
    for i in range(C-3):
        # 0000인 경우
        if height[i] == height[i+1] and height[i+1] == height[i+2] and height[i+2] == height[i+3]:
            ans += 1
elif P == 2:
    for i in range(C-1):
        # 00
        if height[i] == height[i+1]:
            ans += 1

elif P == 3:
    # 001
    for i in range(C-2):
        if height[i] == height[i+1] and height[i+1] == height[i+2] - 1:
            ans += 1

    # 10
    for i in range(C - 1):
        if height[i] == height[i + 1] + 1:
            ans += 1

elif P == 4:
    # 100
    for i in range(C-2):
        if height[i] == height[i+1] + 1 and height[i+1] == height[i+2]:
            ans += 1

    # 01
    for i in range(C - 1):
        if height[i] == height[i + 1] - 1:
            ans += 1

elif P == 5:
    for i in range(C-2):
        # 000
        if height[i] == height[i+1] and height[i+1] == height[i+2]:
            ans += 1

        # 101
        if height[i] == height[i+1] + 1 and height[i+1] + 1 == height[i+2]:
                ans += 1

    for i in range(C - 1):
        # 01
        if height[i] == height[i + 1] - 1:
            ans += 1

        # 10
        if height[i] == height[i+1] + 1:
            ans += 1

elif P == 6:
    for i in range(C-2):
        # 000
        if height[i] == height[i+1] and height[i+1] == height[i+2]:
            ans += 1
        # 011
        if height[i] == height[i+1] - 1 and height[i+1] == height[i+2]:
            ans += 1

    for i in range(C-1):
        # 00
        if height[i] == height[i+1]:
            ans += 1
        # 20
        if height[i] == height[i+1] + 2:
            ans += 1

elif P == 7:
    for i in range(C - 2):
        # 000
        if height[i] == height[i + 1] and height[i + 1] == height[i + 2]:
            ans += 1
        # 110
        if height[i] == height[i + 1] and height[i + 1] == height[i + 2] + 1:
            ans += 1

    for i in range(C - 1):
        # 00
        if height[i] == height[i + 1]:
            ans += 1
        # 02
        if height[i] == height[i + 1] - 2:
            ans += 1

print(ans)