import heapq


def solution(stones, k):
    answer = max(stones) + 1
    heap = []

    for i in range(k - 1):
        heapq.heappush(heap, (-stones[i], i))

    for i in range(k - 1, len(stones)):
        heapq.heappush(heap, (-stones[i], i))
        while heap and heap[0][1] < i - k + 1:
            heapq.heappop(heap)
        answer = min(answer, -heap[0][0])

    return answer
