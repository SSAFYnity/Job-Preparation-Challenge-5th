def search(place):
    directions = [
        ((-1, 0), (-2, 0)), ((1, 0), (2, 0)), ((0, -1), (0, -2)), ((0, 1), (0, 2)),
        ((0, -1), (-1, -1)), ((1, 0), (1, -1)), ((0, -1), (1, -1)), ((0, 1), (1, 1))  # 대각
    ]
    people = [(i, j) for i in range(5) for j in range(5) if place[i][j] == "P"]  # 데이터 분리

    for i in people:
        y, x = i
        for d in directions:
            flag = False
            for dy, dx in d:
                my, mx = y + dy, x + dx
                if 0 <= my < 5 and 0 <= mx < 5:
                    if place[my][mx] == "X":
                        flag = True
                    if not flag and place[my][mx] == "P" and abs(y - my) + abs(x - mx) <= 2:
                        return 0

    return 1


def solution(places):
    answer = []
    for i in range(5):
        for j in range(5):
            places[i][j] = list(places[i][j])

    for i in range(5):
        answer.append(search(places[i]))

    return answer