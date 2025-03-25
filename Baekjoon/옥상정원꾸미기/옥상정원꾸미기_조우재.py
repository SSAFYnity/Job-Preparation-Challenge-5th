import sys
input = lambda: sys.stdin.readline().rstrip()


class Main:
    def __init__(self):
        self.n = int(input())
        self.building = [int(input()) for _ in range(self.n)]
        self.answer = 0

    def solve(self):
        stack = []
        for i in range(self.n):
            while stack and stack[-1] <= self.building[i]:
                stack.pop()
            stack.append(self.building[i])
            self.answer += len(stack) - 1  # i인 자기자신 제외

        print(self.answer)


problem = Main()
problem.solve()