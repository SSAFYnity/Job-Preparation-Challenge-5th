import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;    // 행 개수
        int M = board[0].length; // 열 개수

        int[][] points = new int[N + 1][M + 1];

        // 스킬 적용 (누적합 배열 생성)
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = s[5] * (type == 1 ? -1 : 1);

            points[r1][c1] += degree;
            points[r1][c2 + 1] -= degree;
            points[r2 + 1][c1] -= degree;
            points[r2 + 1][c2 + 1] += degree;
        }

        // 누적합 계산 (좌우 방향)
        for (int y = 0; y < N; y++) {
            for (int x = 1; x < M; x++) {
                points[y][x] += points[y][x - 1];
            }
        }

        // 누적합 계산 (위아래 방향)
        for (int x = 0; x < M; x++) {
            for (int y = 1; y < N; y++) {
                points[y][x] += points[y - 1][x];
            }
        }

        // 파괴되지 않은 건물 개수 세기
        int answer = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                board[y][x] += points[y][x];
                if (board[y][x] > 0) answer++;
            }
        }

        return answer;
    }
}
