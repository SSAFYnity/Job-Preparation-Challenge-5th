def solution(sequence):
    answer = 0
    N = len(sequence)
    # dp[i][j] :
    # j -> 0일 경우 sequence[i]에 -1을 곱했을 때 i번째 수까지의 연속 펄스 부분 수열의 합 중 가장 큰 수
    # j -> 1일 경우 sequence[i]에 1을 곱했을 때 i번째 수까지의 연속 펄스 부분 수열의 합 중 가장 큰 수

    dp = [[0] * 2 for _ in range(N)]
    dp[0][0] = sequence[0] * -1
    dp[0][1] = sequence[0] * 1
    for i in range(1, N):
        dp[i][0] = sequence[i] * -1
        dp[i][1] = sequence[i] * 1

        dp[i][0] = max(dp[i][0], dp[i - 1][1] + dp[i][0])
        dp[i][1] = max(dp[i][1], dp[i - 1][0] + dp[i][1])

    for i in range(N):
        answer = max(answer, dp[i][0], dp[i][1])

    return answer