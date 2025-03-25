# 핵심은 stack 안의 원소 개수 활용
# 내가 볼 수 있는 옥상의 수가 아니라 나를 볼 수 있는
import sys
input = sys.stdin.readline

N = int(input())
ans = 0
buildings = [int(input()) for _ in range(N)]
stack = []
for height in buildings:
    while stack and stack[-1] <= height:
        stack.pop() # 나를 볼 수 없는 관리인 제거

    ans += len(stack) # 나를 볼 수 있는 관리인 수 추가
    stack.append(height) # 현재 빌딩 높이 추가

print(ans)