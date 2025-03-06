from heapq import heappush, heappop
def solution(jobs):
    jobs.sort()
    # 우선순위 : 소요시간 짧, 작업 요청 시간 긴, 작업 번호 작은 것 순
    # job = 요청시간, 소요시간
    h = [(jobs[0][1],jobs[0][0],0)]
    time = jobs[0][0]
    delay = 0
    index = 1
    while h or index < len(jobs):
        if index < len(jobs) and time >= jobs[index][0]:
            heappush(h,(jobs[index][1],jobs[index][0],index))
            index+=1
            continue
        if h:
            l,s,i = heappop(h)
            if time < s:
                time = s
                heappush(h,(l,s,i))
                continue
            time += l
            delay += time - s
        else:
            time += 1
    answer = delay // len(jobs)
    return answer