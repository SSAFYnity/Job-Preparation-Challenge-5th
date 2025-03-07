class Main:
    def __init__(self):
        self.grid = [list(map(int, input().split())) for _ in range(10)]
        self.paper = [5] * 5
        self.answer = 26

    def is_valid(self, sy, sx, ey, ex):  # 해당 영역에 색종이를 붙일 수 있는지 확인
        for y in range(sy, ey + 1):
            for x in range(sx, ex + 1):
                if not self.grid[y][x]:
                    return False
        return True

    def do_action(self, sy, sx, ey, ex, state):
        for y in range(sy, ey + 1):
            for x in range(sx, ex + 1):
                self.grid[y][x] = state

    def search(self, sy=0, cnt=0):
        if cnt >= self.answer:  # 가지치기
            return

        for y in range(sy, 10):  # 내려오면서 확인했으므로 다시 위에서부터 볼 필요 없음
            for x in range(10):  # 항상 x는 0부터 시작해야 함
                if self.grid[y][x]:  # 첫 번째 1 찾기
                    for size in range(5):
                        my, mx = y + size, x + size
                        if self.paper[size] and my < 10 and mx < 10 and self.is_valid(y, x, my, mx):
                            self.do_action(y, x, my, mx, 0)
                            self.paper[size] -= 1
                            self.search(y, cnt + 1)
                            self.paper[size] += 1
                            self.do_action(y, x, my, mx, 1)

                    return  # 현재 위치에서 모든 색종이 확인 끝났으면 더 진행할 필요 없음

        self.answer = min(self.answer, cnt)

    def solve(self):
        self.search()
        print(-1 if self.answer == 26 else self.answer)


problem = Main()
problem.solve()
