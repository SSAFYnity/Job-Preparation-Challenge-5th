from itertools import combinations


def solution(relation):
    row = len(relation)
    col = len(relation[0])
    candidates = []

    for i in range(1, col + 1):  # 가능한 모든 속성 조합
        for comb in combinations(range(col), i):  # 유일성 체크
            temp = {tuple(item[j] for j in comb) for item in relation}
            if len(temp) == row:  # 중복이 없다면 유일성 만족
                if not any(set(candidate).issubset(set(comb)) for candidate in
                           candidates):  # 최소성 체크 -> 후보키가 유일키의 서브셋이라면 해당 유일키는 후보키가 불가능
                    candidates.append(comb)

    return len(candidates)
