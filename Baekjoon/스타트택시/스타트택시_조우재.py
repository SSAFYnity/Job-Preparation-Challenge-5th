from collections import deque


class Main:
    def __init__(self):
        self.n, self.m, self.energy = map(int, input().split())  # 격자 크기, 태울 손님의 수, 초기 연료
        self.grid = [list(map(int, input().split())) for _ in range(self.n)]
        self.y, self.x = map(lambda i: int(i) - 1, input().split())  # 택시의 초기 위치
        self.passengers = [tuple(map(lambda i: int(i) - 1, input().split())) for _ in range(self.m)]  # 손님의 위치(행, 열), 도착지(행, 열)
        self.arrive = [False] * self.m
        self.pick_up = -1

    def cal_distance(self, pick):  # 1 = 태우기, 0 = 내리기
        dq = deque([(self.y, self.x)])
        visited = [[-1] * self.n for _ in range(self.n)]
        visited[self.y][self.x] = 0

        while dq:
            y, x = dq.popleft()

            for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                my, mx = y + dy, x + dx
                if 0 <= my < self.n and 0 <= mx < self.n and not self.grid[my][mx] and visited[my][mx] == -1:
                    dq.append((my, mx))
                    visited[my][mx] = visited[y][x] + 1

        if pick:
            result = []
            for i in range(self.m):
                if self.arrive[i]:  # 도착한 애들은 무시
                    continue
                sy, sx, ey, ex = self.passengers[i]
                if (self.y, self.x) == (sy, sx):
                    return 0, sy, sx, i  # 택시와 승객이 같은 위치에 있을 경우 거리 0 반환
                if visited[sy][sx] != -1:
                    result.append((visited[sy][sx], sy, sx, i))

            result.sort(key=lambda p: (p[0], p[1], p[2]))
            return result[0] if result else []
        else:
            _, _, y, x = self.passengers[self.pick_up]
            return (visited[y][x], y, x, self.pick_up) if visited[y][x] != -1 else []

    def move_taxi(self, pick):
        result = self.cal_distance(pick)
        if not result:  # 승객을 태울 수 없는 경우
            return False
        distance, y, x, i = result

        if self.energy < distance:  # 가는 길에 연료가 다 소모되면
            return False

        if pick:
            self.energy -= distance
            self.pick_up = i
        else:
            self.energy += distance
            self.pick_up = -1
            self.arrive[i] = True
        self.y, self.x = y, x

        return True

    def solve(self):
        while True:
            if sum(self.arrive) == self.m:  # 모든 손님을 다 목적지에 데려다 줬다면
                print(self.energy)
                return
            if not self.move_taxi(True):  # 태우러 감
                print(-1)
                return
            if not self.move_taxi(False):  # 내려주러 감
                print(-1)
                return


problem = Main()
problem.solve()