#include <iostream>
#include <algorithm>

using namespace std;


int n;
int bamboo[501][501];
int dp[501][501];

// 상하좌우 이동 방향
int dr[4] = {-1, 1, 0, 0};
int dc[4] = {0, 0, -1, 1};


// (r, c)에서 출발했을 때 이동할 수 있는 최대 칸 수를 반환하는 함수
int dfs(int r, int c)
{
    // 이미 계산된 적이 있으면 그 값을 바로 반환
    if (dp[r][c] != 0) return dp[r][c];

    // 최소 1칸은 방문 가능하므로 1로 초기화
    dp[r][c] = 1;

    // 4방향에 대해 더 많은 대나무가 있는 칸으로 이동
    for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];

        // 범위 체크 및 대나무 양 체크
        if (nr >= 0 &&
            nr < n &&
            nc >= 0 &&
            nc < n) {
            if (bamboo[nr][nc] > bamboo[r][c]) {
                dp[r][c] = max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
    }

    return dp[r][c];
}


int main()
{
    cin >> n;

    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
            cin >> bamboo[r][c];
        }
    }

    int answer = 0;

    // 모든 칸에 대해 dfs를 수행하여 최댓값을 찾는다.
    for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
            answer = max(answer, dfs(r, c));
        }
    }

    cout << answer << "\n";

    return 0;
}
