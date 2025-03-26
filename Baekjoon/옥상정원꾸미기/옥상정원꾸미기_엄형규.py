import sys
input = sys.stdin.readline

# 빌딩이 n
# 다른 정원 보고 싶다. 오른쪽만 볼수 있고 같거나 높은 빌딩 나오면 그다음 볼수 없다.
N = int(input())
buildings = list(int(input()) for i in range(N))

# q에 값, 인덱스를 넣고 빼면서 그 인덱스의 차이를 더해준다.
q = [(buildings[0],0)]
answer = 0
for i in range(1,N,1):
    repeat = True
    while(repeat and q):
        # 빌딩이 높거나 크면 낮을때까지 더해주기
        if buildings[i] >= q[-1][0]:
            v, index = q.pop()
            answer += (i - index - 1)
            continue
        # 낮으면 낮은 빌딩 넣어주기
        else:
            repeat = False
    q.append((buildings[i],i))
# 남은 빌딩들 계산해주기
print(q)
# N-1-index
while(q):
    v, index = q.pop()
    answer += (N-1-index)
print(answer)