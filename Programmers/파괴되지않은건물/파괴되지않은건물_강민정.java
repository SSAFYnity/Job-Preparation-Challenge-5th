class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;

        // 누적합을 계산한 배열을 얻음
        int[][] sum = makeSumArr(skill, N, M);

        int answer = 0;
        // board와 sum 배열을 더해서 살아남은 칸의 개수를 셈
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // board의 값과 skill의 영향을 합친 결과가 0보다 큰지 확인
                if (board[i][j] + sum[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static int[][] makeSumArr(int[][] skill, int N, int M) {
        int[][] result = new int[N + 1][M + 1];

        // skill 배열을 통해 결과 배열에 영향을 반영
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int degree = skill[i][5];
            if (type == 1) {  // 공격
                degree *= -1;
            }

            // (startX, startY)부터 (endX, endY)까지 영향을 반영
            result[skill[i][1]][skill[i][2]] += degree;
            if (skill[i][4] + 1 < M) {
                result[skill[i][1]][skill[i][4] + 1] -= degree;
            }
            if (skill[i][3] + 1 < N) {
                result[skill[i][3] + 1][skill[i][2]] -= degree;
            }
            if (skill[i][3] + 1 < N && skill[i][4] + 1 < M) {
                result[skill[i][3] + 1][skill[i][4] + 1] += degree;
            }
        }

        // 가로 누적합 계산
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                result[i][j] += result[i][j - 1];
            }
        }

        // 세로 누적합 계산
        for (int j = 0; j < M + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                result[i][j] += result[i - 1][j];
            }
        }

        return result;
    }
}
