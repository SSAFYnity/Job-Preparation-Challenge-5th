from itertools import permutations


def is_match(user, banned):
    if len(user) != len(banned):
        return False
    for u, b in zip(user, banned):
        if b != '*' and u != b:
            return False
    return True


def solution(user_id, banned_id):
    answer = set()

    for perm in permutations(user_id, len(banned_id)):
        if all(is_match(perm[i], banned_id[i]) for i in range(len(banned_id))):  # 모두가 True이면 동작
            answer.add(tuple(sorted(perm)))  # 리스트 unhashable로 변경, 일관된 데이터 형태 유지를 위해 정렬

    return len(answer)
