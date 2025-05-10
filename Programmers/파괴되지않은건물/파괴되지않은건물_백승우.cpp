#include <vector>
using namespace std;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int N = board.size();    // 행 크기
    int M = board[0].size(); // 열 크기

    // diff 배열을 (N+1)x(M+1)로 구성 (모든 원소를 0으로 초기화)
    vector<vector<int>> diff(N + 1, vector<int>(M + 1, 0));

    // [#1] skill 정보를 diff 배열에 누적
    for (auto &s : skill) {
        int type = s[0];
        int r1 = s[1];
        int c1 = s[2];
        int r2 = s[3];
        int c2 = s[4];
        int degree = s[5];


        int val;
        if (type == 1) val = -degree; // 파괴
        else val = degree; // 복구

        // diff 업데이트 (가능하면 c2+1, r2+1이 범위 내에 있는지 체크)
        diff[r1][c1] += val;

        if (c2 + 1 <= M - 1) diff[r1][c2 + 1] -= val;
        if (r2 + 1 <= N - 1) diff[r2 + 1][c1] -= val;
        if (r2 + 1 <= N - 1 && c2 + 1 <= M - 1) diff[r2 + 1][c2 + 1] += val;
    }

    // [#2] diff 배열에 대해 행 방향 누적 합
    for (int i = 0; i < N; i++) {
        for (int j = 1; j < M; j++) {
            diff[i][j] += diff[i][j - 1];
        }
    }

    // [#3] diff 배열에 대해 열 방향 누적 합
    for (int j = 0; j < M; j++) {
        for (int i = 1; i < N; i++) {
            diff[i][j] += diff[i - 1][j];
        }
    }

    // [#4] 최종적으로 board에 diff 값을 더해주면서 내구도가 1 이상인 것 카운팅
    int answer = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            board[i][j] += diff[i][j];

            if (board[i][j] > 0) { // 1 이상이면 파괴되지 않은 건물
                answer++;
            }
        }
    }

    return answer;
}
