import java.util.*;

class Solution {
    
    int N, M;
    int[][] lock;
    int[][] key;
    boolean answer;
    
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        
        this.lock = new int[N + 2 * M - 2][N + 2 * M - 2];
        this.key = key;
        
        for (int i = M - 1; i < M - 1 + N; i++) {
            for (int j = M - 1; j < M - 1 + N; j++) {
                this.lock[i][j] = lock[i - M + 1][j - M + 1];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (match()) {
                answer = true;
                break;
            }
            rotateKey();
        }
        return answer;
    }
    
    private boolean match() {
        int len = N + M - 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum(i, j);

                boolean flag = true;
                for (int k = M - 1; k < len; k++) {
                    for (int l = M - 1; l < len; l++) {
                        if (lock[k][l] != 1) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    return true;
                }
                
                subtract(i, j);            
            }
        }
        return false;
    }
    
    private void sum(int x, int y) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                lock[x + i][y + j] += key[i][j];
            }
        }
    }
    
    private void subtract(int x, int y) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                lock[x + i][y + j] -= key[i][j];
            }
        }
    }    
    
    private void rotateKey() {
        int[][] tmp = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = key[j][M - i - 1];
            }
        }
        key = tmp;
    }
}
