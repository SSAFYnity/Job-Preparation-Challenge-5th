from itertools import permutations

def solution(user_id, banned_id):
    answer = []
    N = len(banned_id)
    for perm in permutations(user_id, N):
        possible = True
        for i in range(N):
            if len(perm[i]) != len(banned_id[i]):
                possible = False
                break
            else:
                for j in range(len(banned_id[i])):
                    if banned_id[i][j] == '*':
                        continue
                    elif perm[i][j] != banned_id[i][j]:
                        possible = False
                        break
                if not possible:
                    break
        if possible:
            if set(perm) not in answer:
                answer.append(set(perm))
    return len(answer)