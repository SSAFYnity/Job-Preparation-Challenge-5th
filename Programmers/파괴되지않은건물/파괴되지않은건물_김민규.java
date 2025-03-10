import java.util.*;
import java.io.*;

class Solution {
    public static int[][] map;
    
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] diff = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0] == 1 ? -1 : 1; // 1이면 공격이니까 음수 처리
            int degree = s[5] * type;
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];

            diff[r1][c1] += degree; // 각 지점을 기록해둔다 (어차피 그 구간은 똑같은 값이기 때문에)
            diff[r1][c2 + 1] -= degree; // 빼주는 이유는 보정값 때문에(어차피 대각선 처리 할거라서)
            diff[r2 + 1][c1] -= degree; 
            diff[r2 + 1][c2 + 1] += degree;
        }

        // 가로 누적합
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // 세로 누적합
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += diff[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
    
    public static void go(int x1, int y1, int x2, int y2, int d){
        for(int i=x1; i<=x2; i++){
            for(int j=y1; j<=y2; j++){
                map[i][j] += d;
            }
        }
    }
}