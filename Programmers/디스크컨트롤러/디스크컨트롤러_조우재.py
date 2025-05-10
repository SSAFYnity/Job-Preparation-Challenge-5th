import heapq

def solution(jobs):
    jobs.sort()
    heap = []
    current_time = total_time = 0
    job = 0
    completed_jobs = 0

    while completed_jobs < len(jobs):  # [작업이 요청되는 시점, 작업의 소요시간]
        while job < len(jobs) and jobs[job][0] <= current_time:
            request_time, duration = jobs[job]
            heapq.heappush(heap, (duration, request_time))  # 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것
            job += 1

        if heap:
            duration, request_time = heapq.heappop(heap)
            current_time += duration
            total_time += (current_time - request_time)
            completed_jobs += 1
        else:
            current_time = jobs[job][0]

    return total_time // len(jobs)