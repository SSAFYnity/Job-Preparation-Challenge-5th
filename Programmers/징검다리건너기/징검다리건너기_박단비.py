# 연속된 K개의 디딤돌에 적힌 숫자가 모두 0인 구간이 있으면 더이상 징검다리를 건널 수 없음
def solution(stones, k):
    answer = 0
    left = 1
    right = max(stones)
    while left <= right:
        mid = (left + right) // 2
        cnt = 0  # 디딤돌에 적힌 숫자가 연속으로 0인 갯수
        for stone in stones:
            if stone - mid <= 0:
                cnt += 1
            else:
                cnt = 0

            if cnt >= k:
                break

        if cnt < k:
            left = mid + 1

        else:
            answer = mid
            right = mid - 1

    return answer