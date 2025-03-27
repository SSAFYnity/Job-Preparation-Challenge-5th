from heapq import *
import sys
input = lambda: sys.stdin.readline().rstrip()


class Main:
    def __init__(self):
        self.n, self.m = map(int, input().split())
        self.info = [tuple(map(int, input().split())) for _ in range(self.m)]
        self.answer = 0

    def search(self, start, graph):
        cost_table = [float('inf')] * (self.n + 1)
        cost_table[start] = 0
        hq = [(0, 1)]

        while hq:
            current_cost, current = heappop(hq)

            if cost_table[current] < current_cost:
                continue

            for i in graph[current]:
                nxt_cost, nxt = i
                new_cost = current_cost + nxt_cost
                if new_cost < cost_table[nxt]:
                    cost_table[nxt] = new_cost
                    heappush(hq, (new_cost, nxt))

        print(cost_table[self.n])

    def solve(self):
        graph: dict[int, list[tuple]]
        graph = {i: [] for i in range(1, self.n + 1)}
        for i in self.info:
            s, e, c = i
            graph[s].append((c, e))
            graph[e].append((c, s))

        self.search(1, graph)


problem = Main()
problem.solve()
