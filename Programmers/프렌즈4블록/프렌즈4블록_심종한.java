import java.util.*;

class Solution {
    
    int N, M;
    char[][] arr;
    boolean[][] visited;
    final char EMPTY = '-';
    
    public int solution(int m, int n, String[] board) {
        N = n;
        M = m;
        arr = new char[M][N];
        for (int i = 0; i < M; i++) {
            arr[i] = board[i].toCharArray();
        }
        
        // 블록 제거
        boolean flag = true;
        while(flag) {
            visited = new boolean[M][N];
            flag = remove();
        }
        
        // 남은 블록 계산
        int answer = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == EMPTY) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private boolean remove() {
        boolean isFinished = true;
        // 제거 대상 선정
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (isPossible(i, j)) {
                    mark(i, j);
                    isFinished = false;
                }
            }
        }
        // 제거할 대상이 없으면 종료
        if (isFinished) {
            return false;
        }
        // 블록 제거
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    arr[i][j] = EMPTY;
                }
            }
        }
        drop();
        return true;
    }
    
    private void drop() {
        for (int j = 0; j < N; j++) {
            // 임시 저장 
            Queue<Character> q = new ArrayDeque<>();
            for (int i = M - 1; i >= 0; i--) {
                if (arr[i][j] != EMPTY) {
                    q.offer(arr[i][j]);
                }
            }
            // 밑에서부터 삽입
            for (int i = M - 1; i >= 0; i--) {
                if (!q.isEmpty()) {
                    arr[i][j] = q.poll();
                } else {
                    arr[i][j] = EMPTY;
                }
            }
        }        
    }
    
    private boolean isPossible(int i, int j) {
        if (arr[i][j] == EMPTY || i + 1 >= M || j + 1 >= N) {
            return false;
        }
        return arr[i][j] == arr[i + 1][j] &&
               arr[i][j] == arr[i][j + 1] &&
               arr[i][j] == arr[i + 1][j + 1];
    }
    
    private void mark(int i, int j) {
        visited[i][j] = true;
        visited[i][j + 1] = true;
        visited[i + 1][j] = true;
        visited[i + 1][j + 1] = true;
    }
}
