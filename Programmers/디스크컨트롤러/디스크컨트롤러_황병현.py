from heapq import heappush, heappop

def solution(jobs):
    answer = 0
    jobs.sort()
    
    n = len(jobs)
    heap = []
    idx = 0
    current_time = 0
    
    while idx < n or heap:
        while idx < n and current_time >= jobs[idx][0]:
            heappush(heap, (jobs[idx][1], jobs[idx][0], idx))
            idx += 1
                
        if heap:
            time, start, now = heappop(heap)
            current_time += time
            answer += current_time - start
        else:
            current_time = jobs[idx][0]
    
    return answer // n