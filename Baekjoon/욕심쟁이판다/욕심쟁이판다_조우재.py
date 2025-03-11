import sys
sys.setrecursionlimit(10**5)


class Main:
    def __init__(self):
        self.n = int(input())
        self.graph = [list(map(int, input().split())) for _ in range(self.n)]
        self.visited = [[0] * self.n for _ in range(self.n)]
        self.directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        self.answer = 0

    def search(self, y, x):
        if self.visited[y][x]:  # 이미 방문한 곳이라면
            return self.visited[y][x]  # 더 이상 주변에 방문할 곳이 없다는 것을 의미

        self.visited[y][x] = 1
        for dy, dx in self.directions:
            my, mx = dy + y, dx + x
            if 0 <= my < self.n and 0 <= mx < self.n and self.graph[my][mx] > self.graph[y][x]:
                self.visited[y][x] = max(self.visited[y][x], self.search(my, mx) + 1)

        return self.visited[y][x]

    def solve(self):
        for i in range(self.n):
            for j in range(self.n):
                if not self.visited[i][j]:  # 해당 시작점에 대한 기록이 없다면
                    self.answer = max(self.answer, self.search(i, j))  # 어느 깊이까지 갈 수 있는지 탐색

        print(self.answer)


problem = Main()
problem.solve()