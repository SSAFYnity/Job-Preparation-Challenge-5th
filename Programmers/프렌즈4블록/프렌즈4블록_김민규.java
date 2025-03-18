import java.util.*;

class Solution {
    static char[][] b;
    static int r, c;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        r = m;
        c = n;
        b = new char[r][c];
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                b[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            int removed = bomb();
            if (removed == 0) break; // 제거된게 없다? -> 끝
            answer += removed;
            apply();
        }
        
        return answer;
    }
    
    public int bomb() {
        boolean[][] v = new boolean[r][c];
        int count = 0;
        
        // 터뜨릴 블록 찾기
        for (int i = 0; i < r - 1; i++) {
            for (int j = 0; j < c - 1; j++) {
                if (b[i][j] != 'X' && isTxT(i, j)) {
                    v[i][j] = v[i + 1][j] = v[i][j + 1] = v[i + 1][j + 1] = true;
                }
            }
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (v[i][j]) {
                    b[i][j] = 'X';
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public boolean isTxT(int ci, int cj) {
        return b[ci][cj] == b[ci + 1][cj] && 
               b[ci][cj] == b[ci + 1][cj + 1] && 
               b[ci][cj] == b[ci][cj + 1];
    }
    
    public void apply() {
        // 열 기준으로 아래로 블록을 당기기
        for (int j = 0; j < c; j++) {
            List<Character> list = new ArrayList<>();
            
            // 블록 다 들고
            for (int i = 0; i < r; i++) {
                if (b[i][j] != 'X') {
                    list.add(b[i][j]);
                }
            }
            
            // 아래부터 채우기
            int index = r - 1;
            for (int i = list.size() - 1; i >= 0; i--) {
                b[index--][j] = list.get(i);
            }
            
            while (index >= 0) {
                b[index--][j] = 'X';
            }
        }
    }
}