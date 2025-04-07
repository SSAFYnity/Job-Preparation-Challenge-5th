from heapq import *


class Main:
    def __init__(self):
        self.n, self.m = map(int, input().split())
        self.times = list(map(int, input().split()))
        self.answer = 0

    def solve(self):
        hq = []
        for time in self.times:  # 충전 시간이 오래 걸리는 것끼리 묶어야 이득
            heappush(hq, (-time, time))

        while len(hq) > self.m:
            min_num = 2**15 + 1
            temp = []
            for i in range(self.m):
                weight, num = heappop(hq)
                min_num = min(min_num, num)  # 공통 충전 시간
                temp.append(num)

            for i in temp:
                new_num = i - min_num
                if new_num > 0:  # 충전이 안 됐으면 다시 추가
                    heappush(hq, (-new_num, new_num))

            self.answer += min_num

        print(self.answer if not hq else self.answer + heappop(hq)[1])


problem = Main()
problem.solve()