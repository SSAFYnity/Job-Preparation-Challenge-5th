from collections import deque


class Main:
    def __init__(self):
        self.n = int(input())
        self.k = int(input())
        self.apples = [tuple(map(int, input().split())) for _ in range(self.k)]
        self.l = int(input())
        self.cmd = [input().split() for _ in range(self.l)]

    def init_grid(self):
        grid = [[0] * self.n for _ in range(self.n)]
        for apple in self.apples:
            y, x = apple
            grid[y - 1][x - 1] = 1  # 1은 사과

        return grid

    @staticmethod
    def turn(current_direction, direction):
        return (current_direction + 1) % 4 if direction == 'L' else (current_direction + 7) % 4

    def run(self):
        directions = [(0, 1), (-1, 0), (0, -1), (1, 0)]  # 회전 정보
        grid = self.init_grid()  # 맵 생성
        y, x = 0, 0  # 초기 뱀의 위치
        snake = deque([(0, 0)])  # 초기 뱀
        current_direction = 0  # 초기 뱀의 방향
        time = 0  # 정답

        for end_time, direction in self.cmd:
            while time < int(end_time):  # 방향 전환 시간 포함
                time += 1  # 뱀이 움직이기 시작한 시간
                my, mx = y + directions[current_direction][0], x + directions[current_direction][1]

                if not (0 <= my < self.n and 0 <= mx < self.n) or (my, mx) in snake:  # 게임 종료 조건이 충족됐을 때
                    return time  # 게임 종료

                if grid[my][mx] == 1:  # 사과 발견
                    grid[my][mx] = 0
                else:
                    snake.popleft()  # 머리가 이동했으니 마지막 꼬리의 위치도 변경(제거)
                y, x = my, mx  # 실제 위치 이동
                snake.append((y, x))

            current_direction = self.turn(current_direction, direction)  # 방향 전환

        while True:  # 아직 게임이 종료되지 않았다면
            time += 1
            my, mx = y + directions[current_direction][0], x + directions[current_direction][1]

            if not (0 <= my < self.n and 0 <= mx < self.n) or (my, mx) in snake:
                return time

            snake.append((my, mx))
            snake.popleft()
            y, x = my, mx

    def solve(self):
        print(self.run())


problem = Main()
problem.solve()