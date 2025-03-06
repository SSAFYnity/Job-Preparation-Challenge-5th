def solution(jobs):
    jobs.sort()
    
    now = 0
    total_time = 0
    l = len(jobs)
    while jobs:
        now_jobs = [job for job in jobs if job[0] <= now]
        if now_jobs:
            now_jobs.sort(key=lambda x: x[1])
            pick = now_jobs[0]
            jobs.remove(pick)
            now += pick[1]
            total_time += now - pick[0]
        else:
            now += 1
    
    answer = total_time // l
    return answer
