#include <iostream>
using namespace std;

long long dp[30+1];

int main()
{
    int N;
    cin >> N;

    // 초기값 설정
    dp[0] = 1; // 3×0은 "아무것도 안 놓는 경우"를 1가지
    dp[1] = 0; // 3×1은 채울 수 없으므로 0
    dp[2] = 3; // 3×2는 3가지

    // DP 점화식 계산
    for (int i = 4; i <= N; i += 2) {
        dp[i] = 4 * dp[i - 2] - dp[i - 4];
    }

    cout << dp[N] << endl;
    
    return 0;
}
