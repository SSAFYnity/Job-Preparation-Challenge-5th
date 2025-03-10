import java.util.*;

class Solution {
    
    int N, M;
    int answer = 0;
    int[][] pSum;
    public int solution(int[][] board, int[][] skills) {
        N = board.length;
        M = board[0].length;
        pSum = new int[N + 1][M + 1];
        
        for (int[] skill : skills) {
            mark(skill[0], skill[1], skill[2], skill[3], skill[4], skill[5]);
        }
        imos();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + pSum[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private void mark(int type, int sx, int sy, int gx, int gy, int degree) {
        if (type == 1) {
            degree *= -1;
        }
        pSum[sx][sy] += degree;
        pSum[gx + 1][gy + 1] += degree;
        pSum[sx][gy + 1] -= degree;
        pSum[gx + 1][sy] -= degree;
    }
    
    private void imos() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                pSum[i][j + 1] += pSum[i][j];
            }
        }
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                pSum[i + 1][j] += pSum[i][j];
            }
        }   
    }
}
