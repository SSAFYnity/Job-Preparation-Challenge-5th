def possible(result):
    for x, y, a in result:
        if a == 0:  # 기둥
            if not (y == 0 or (x, y - 1, 0) in result or (x - 1, y, 1) in result or (x, y, 1) in result):
                return False
        else:  # 보
            if not ((x, y - 1, 0) in result or (x + 1, y - 1, 0) in result or
                    ((x - 1, y, 1) in result and (x + 1, y, 1) in result)):
                return False
    return True


def solution(n, build_frame):
    result = set()

    for x, y, a, b in build_frame:
        if b == 0:  # 삭제
            result.remove((x, y, a))  # 일단 삭제
            if not possible(result):  # 정합성 깨지는지 확인
                result.add((x, y, a))  # 깨지면 다시 추가
        else:  # 설치
            result.add((x, y, a))  # 일단 추가
            if not possible(result):  # 정합성 깨지는지 확인
                result.remove((x, y, a))  # 깨지면 다시 제거

    return sorted(result, key=lambda i: (i[0], i[1], i[2]))
