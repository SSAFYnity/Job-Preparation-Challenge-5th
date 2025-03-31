import sys
input = lambda: sys.stdin.readline().rstrip()


class Main:
    def __init__(self):
        self.c = int(input())
        self.selected = [False] * 11
        self.answer = []

    def search(self, player, ability_sum, status):
        if player == 11:
            self.answer.append(ability_sum)
            return

        for i in range(11):
            if not self.selected[i] and status[player][i] > 0:  # i = position status
                self.selected[i] = True  # 현재 포지션 선정 완료
                self.search(player + 1, ability_sum + status[player][i], status)  # 다음 선수 확인
                self.selected[i] = False

    def solve(self):
        for _ in range(self.c):
            status = [list(map(int, input().split())) for _ in range(11)]
            self.search(0, 0, status)
            print(max(self.answer))
            self.answer = []


problem = Main()
problem.solve()
