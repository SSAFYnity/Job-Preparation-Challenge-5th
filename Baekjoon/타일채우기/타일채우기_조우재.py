class Main:
    def __init__(self):
        self.n = int(input())

    def solve(self):
        dp = [0] * 31
        dp[0] = 1
        dp[2] = 3

        for i in range(4, 31, 2):
            dp[i] = dp[i - 2] * dp[2]  # 고유 형태를 제외하고 만들 수 있는 경우의 수
            for j in range(i - 4, 1, -2):
                dp[i] += dp[j] * 2  # 직전의 고유 형태를 조합해서 만들 수 있는 경우의 수 -> 고유 형태는 항상 2개
            dp[i] += 2  # 새로 생기는 고유 형태

        print(dp[self.n])


problem = Main()
problem.solve()