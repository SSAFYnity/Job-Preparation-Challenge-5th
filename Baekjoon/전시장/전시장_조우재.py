import sys
from bisect import *
input = sys.stdin.readline


class Main:
    def __init__(self):
        self.n, self.s = map(int, input().split())
        self.arts = [tuple(map(int, input().split())) for _ in range(self.n)]

    def solve(self):
        self.arts.sort()
        dp = [0] * self.n
        positions = [art[0] for art in self.arts]

        for i in range(self.n):
            h, c = self.arts[i]
            j = bisect_right(positions, h - self.s) - 1  # s 이상 떨어진 가장 가까운 이전 위치 찾기 (이분 탐색)
            if j >= 0:
                dp[i] = max(dp[i - 1] if i > 0 else 0, dp[j] + c)  # j 번째 예술품 고려
            else:
                dp[i] = max(dp[i - 1] if i > 0 else 0, c)  # 첫 번째 예술품 처리

        print(dp[-1])


problem = Main()
problem.solve()
