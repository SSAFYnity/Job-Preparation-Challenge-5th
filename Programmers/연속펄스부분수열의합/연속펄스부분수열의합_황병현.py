def solution(sequence):
    dp1 = [0] * len(sequence)
    dp2 = [0] * len(sequence)
    dp1[0] = -1 * sequence[0]
    dp2[0] = sequence[0]
    
    check = [-1,1]
    answer = max(dp1[0],dp2[0])
    for i in range(1,len(sequence)):
        if i%2 == 0:
            dp1[i] = max(dp1[i-1] + sequence[i] * check[0], sequence[i] * check[0])
            dp2[i] = max(dp2[i-1] + sequence[i] * check[1], sequence[i] * check[1])
        else:
            dp1[i] = max(dp1[i-1] + sequence[i] * check[1], sequence[i] * check[1])
            dp2[i] = max(dp2[i-1] + sequence[i] * check[0], sequence[i] * check[0])
        answer = max(answer, dp1[i], dp2[i])
            
    return answer