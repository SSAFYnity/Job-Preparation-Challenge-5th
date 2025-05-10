import sys
input = lambda: sys.stdin.readline().rstrip()


class Main:
    def __init__(self):
        self.tc = int(input())
        self.answer = []
        self.parents = None
        self.children = None

    def find(self, x):
        if self.parents[x] == x:
            return x
        self.parents[x] = self.find(self.parents[x])
        return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return self.children[x]

        self.parents[x] = y  # x에 자식 y 연결
        self.children[y] = self.children[x] + self.children[y]  # 연결되는 자식 y 정보 갱신
        return self.children[y]  # 연결된 정보 반환

    def solve(self):
        for _ in range(self.tc):
            f = int(input())
            self.parents = dict()
            self.children = dict()
            for _ in range(f):
                friend1, friend2 = input().split()

                if friend1 not in self.parents:
                    self.parents[friend1] = friend1
                    self.children[friend1] = 1  # 친구 수

                if friend2 not in self.parents:
                    self.parents[friend2] = friend2
                    self.children[friend2] = 1  # 친구 수

                self.answer.append(self.union(friend1, friend2))

        for i in self.answer:
            print(i)


problem = Main()
problem.solve()