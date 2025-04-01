def solution(sequence):
    n = len(sequence)
    answer = 0
    # [1,-1,...]
    even = 0
    # [-1,1,...]
    odd = 0
    for i in range(n):
        # 펄스 곱한 수
        pulse_num = (sequence[i] if i%2 == 0 else -sequence[i])
        even = max(pulse_num, even + pulse_num)
        odd = min(pulse_num, odd + pulse_num)
        answer = max(answer, even, -odd)
    return answer
