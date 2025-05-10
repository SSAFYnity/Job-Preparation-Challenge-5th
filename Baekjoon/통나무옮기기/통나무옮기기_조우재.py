from collections import deque


class Main:
    def __init__(self):
        self.n = int(input())
        self.grid = [list(input()) for _ in range(self.n)]
        self.answer = float("inf")
        self.directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def init_point(self):
        start = []
        end = []
        for i in range(self.n):
            for j in range(self.n):
                if self.grid[i][j] == "B":
                    start.append((i, j))
                    self.grid[i][j] = "0"
                elif self.grid[i][j] == "E":
                    end.append((i, j))
                    self.grid[i][j] = "0"

        return start, end

    @staticmethod
    def get_centroid(points):
        return [points[1][0], points[1][1], 0 if points[0][0] == points[1][0] else 1]  # ## ㅡ or ㅣ

    def can_move(self, y, x, d):
        if d == 0:  # 가로 방향
            return 0 <= y < self.n and 1 <= x < self.n - 1 and all(self.grid[y][x + dx] == '0' for dx in [-1, 0, 1])
        else:  # 세로 방향
            return 1 <= y < self.n - 1 and 0 <= x < self.n and all(self.grid[y + dy][x] == '0' for dy in [-1, 0, 1])

    def can_rotate(self, y, x):
        return all(0 <= y + dy < self.n and 0 <= x + dx < self.n and self.grid[y + dy][x + dx] == '0'
                   for dy in [-1, 0, 1] for dx in [-1, 0, 1])

    def search(self):
        start, end = self.init_point()
        sy, sx, sd = self.get_centroid(start)
        ey, ex, ed = self.get_centroid(end)
        visited = [[[-1] * 2 for _ in range(self.n)] for _ in range(self.n)]
        visited[sy][sx][sd] = 0
        dq = deque([(sy, sx, sd)])

        while dq:
            y, x, d = dq.popleft()
            if (y, x, d) == (ey, ex, ed):
                print(visited[y][x][d])
                return

            for dy, dx in self.directions:
                my, mx = y + dy, x + dx

                if self.can_move(my, mx, d) and visited[my][mx][d] == -1:
                    visited[my][mx][d] = visited[y][x][d] + 1
                    dq.append((my, mx, d))

            if self.can_rotate(y, x):
                new_d = 1 - d  # 0(가로) <-> 1(세로) 변환
                if visited[y][x][new_d] == -1:
                    visited[y][x][new_d] = visited[y][x][d] + 1
                    dq.append((y, x, new_d))

        print(0)

    def solve(self):
        self.search()
        return


problem = Main()
problem.solve()
