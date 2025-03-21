from collections import deque


class Main:
    def __init__(self):
        self.a_size, self.b_size, self.c_size = map(int, input().split())

    def search(self):
        visited = set()
        result = set()
        dq = deque()
        dq.append((0, 0, self.c_size))
        visited.add((0, 0, self.c_size))

        while dq:
            a, b, c = dq.popleft()  # 각 물병에 담긴 물의 양
            if a == 0:
                result.add(c)

            for new_a, new_b, new_c in [  # 특정 물통으로 물을 옮길 때, 다 옮기는지 or 차이만큼 옮기는지
                (a - min(a, self.b_size - b), b + min(a, self.b_size - b), c),  # a -> b
                (a - min(a, self.c_size - c), b, c + min(a, self.c_size - c)),  # a -> c
                (a + min(b, self.a_size - a), b - min(b, self.a_size - a), c),  # b -> a
                (a, b - min(b, self.c_size - c), c + min(b, self.c_size - c)),  # b -> c
                (a + min(c, self.a_size - a), b, c - min(c, self.a_size - a)),  # c -> a
                (a, b + min(c, self.b_size - b), c - min(c, self.b_size - b))   # c -> b
            ]:
                new_state = (new_a, new_b, new_c)
                if new_state not in visited:
                    visited.add(new_state)
                    dq.append(new_state)

        print(*sorted(result))

    def solve(self):
        self.search()


problem = Main()
problem.solve()
