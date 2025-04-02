class Main:
    def __init__(self):
        self.c, self.p = map(int, input().split())
        self.heights = list(map(int, input().split()))
        self.answer = 0

    def solve(self):
        rotated_blocks = {
            1: [(0, ), (0, 0, 0, 0)],  # I자 블록
            2: [(0, 0)],  # O자 블록
            3: [(0, 0, 1), (1, 0)],  # S자 블록
            4: [(1, 0, 0), (0, 1)],  # Z자 블록
            5: [(0, 0, 0), (0, 1), (1, 0, 1), (1, 0)],  # T자 블록
            6: [(0, 0, 0), (0, 0), (0, 1, 1), (2, 0)],  # L자 블록
            7: [(0, 0, 0), (0, 2), (1, 1, 0), (0, 0)]  # J자 블록
        }  # 바닥 기준 높이

        for shape in rotated_blocks[self.p]:  # 해당 블록의 모든 형태를 확인
            length = len(shape)

            for i in range(self.c - length + 1):  # 필드 범위 내에서 배치 시도
                min_height = min(self.heights[i:i + length])  # 블록이 닿을 최저 높이

                if all(self.heights[i + j] - shape[j] == min_height for j in range(length)):
                    self.answer += 1

        print(self.answer)


problem = Main()
problem.solve()
