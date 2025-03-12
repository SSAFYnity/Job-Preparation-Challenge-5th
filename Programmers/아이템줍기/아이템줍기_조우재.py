from collections import deque


def make_grid(rectangles):
    grid = [[9] * 102 for _ in range(102)]

    for rect in rectangles:
        sx, sy, ex, ey = map(lambda cord: cord * 2, rect)
        for y in range(sy, ey + 1):
            for x in range(sx, ex + 1):
                if sy < y < ey and sx < x < ex:
                    grid[y][x] = 0  # 벽
                elif grid[y][x] == 9:
                    grid[y][x] = 1  # 길

    return grid


def search(grid, characterX, characterY, itemX, itemY):
    dq = deque([(characterY * 2, characterX * 2, 0)])
    visited = [[False] * 102 for _ in range(102)]
    visited[characterY * 2][characterX * 2] = True

    while dq:
        y, x, distance = dq.popleft()
        if (y, x) == (itemY * 2, itemX * 2):
            return distance // 2

        for dy, dx in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            my, mx = y + dy, x + dx
            if grid[my][mx] == 1 and not visited[my][mx]:
                dq.append((my, mx, distance + 1))
                visited[my][mx] = True

    return 0


def solution(rectangle, characterX, characterY, itemX, itemY):
    grid = make_grid(rectangle)
    answer = search(grid, characterX, characterY, itemX, itemY)

    return answer