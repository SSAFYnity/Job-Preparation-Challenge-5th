import heapq
def solution(n, roads, sources, destination):
    # n 지역, 길정보 roads, 부대원 위치 sources, 부대위치 destination
    answer = []
    dp = [-1]*(n+1)
    way = [[] for i in range(n+1)]
    for road in roads:
        way[road[0]].append(road[1])
        way[road[1]].append(road[0])
    h = [(0,destination)]
    while (h):
        time, now = heapq.heappop(h)
        if (dp[now] == -1):
            dp[now] = time
            for i in way[now]:
                heapq.heappush(h,(time+1,i))
    for i in range(len(sources)):
        answer.append(dp[sources[i]])
    
    return answer