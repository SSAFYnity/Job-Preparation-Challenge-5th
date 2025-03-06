# 현재 시점에서 처리할 수 있는 작업들을 힙에 넣기
from heapq import heappush, heappop


def solution(jobs):
    answer = 0
    jobs.sort()

    N = len(jobs)
    heap = []
    idx = 0
    now = 0  # 현재시간

    while idx < N or heap:
        while idx < N and now >= jobs[idx][0]:
            heappush(heap, (jobs[idx][1], jobs[idx][0], idx))
            idx += 1

        if heap:
            time, request, index = heappop(heap)
            now += time
            answer += (now - request)

        else:
            now = jobs[idx][0]

    return answer // N