from collections import deque
def solution(stones, k):
    answer = float('inf')
    q = deque()
    for i, cur in enumerate(stones):
        while q and stones[q[-1]] <= cur:
            q.pop()
        q.append(i)
        if q[0] == i - k:
            q.popleft()
        if i >= k - 1:
            answer = min(answer, stones[q[0]])
    return answer