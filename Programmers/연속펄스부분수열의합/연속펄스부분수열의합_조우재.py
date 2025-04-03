def search(pulse):
    best_optimal = float("-inf")
    optimal = 0
    for num in pulse:
        optimal = max(num, optimal + num)  # 현재 수를 더했을 때 크게 되는지, 현재수만 선택했을 때 크게 되는지
        best_optimal = max(best_optimal, optimal)  # 그 값이 최적의 값인지

    return best_optimal


def solution(sequence):
    n = len(sequence)

    pulse1 = [sequence[i] * (1 if i % 2 == 0 else -1) for i in range(n)]
    pulse2 = [sequence[i] * (-1 if i % 2 == 0 else 1) for i in range(n)]

    return max(search(pulse1), search(pulse2))
