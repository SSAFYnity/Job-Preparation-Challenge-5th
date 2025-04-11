def check(answer):
    for x, y, a in answer:
        # 기둥인 경우
        if a == 0:
            if (y != 0 and [x, y-1, 0] not in answer and [x-1, y, 1] not in answer and [x, y, 1] not in answer):
                return False
        # 보인 경우
        else:
            if ([x, y-1, 0] not in answer and [x + 1, y - 1, 0] not in answer and ([x - 1, y, 1] not in answer or [x + 1, y, 1] not in answer)):
                return False
    return True


def solution(n, build_frame):
    answer = []
    for x, y, a, b in build_frame:
        # 삭제라면
        if b == 0:
            answer.remove([x, y, a])
            # 삭제 후에 check 통과 못하면 재설치
            if not check(answer):
                answer.append([x, y, a])
        # 설치라면
        elif b == 1:
            answer.append([x, y, a])
            # 설치 후에 check 통과 못하면 삭제
            if not check(answer):
                answer.remove([x, y, a])
    answer.sort(key=lambda x : (x[0], x[1], x[2]))
    return answer