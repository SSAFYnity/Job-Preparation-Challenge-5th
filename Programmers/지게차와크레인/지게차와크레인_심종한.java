import java.util.*;

class Solution {
    
    int N, M;
    int[][] storage;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    
    class Pos {
        int x;
        int y; 
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        this.N = storage.length;
        this.M = storage[0].length();
        this.storage = new int[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(this.storage[i], -1);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.storage[i + 1][j + 1] = storage[i].charAt(j) - 'A';
            }
        }
        
        for (String request : requests) {
            if (request.length() == 2) {
                remove(request.charAt(0) - 'A');
            } else {
                bfs(request.charAt(0) - 'A');
            }
        }
        int answer = 0;
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                if (this.storage[i][j] != -1) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private void remove(int target) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (storage[i][j] == target) {
                    storage[i][j] = -1;
                }
            }
        }
    }
    
    private void bfs(int target) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        boolean[][] visited = new boolean[N + 2][M + 2];
        visited[0][0] = true;
        while(!q.isEmpty()) {
            Pos pos = q.poll();
            int nowX = pos.x;
            int nowY = pos.y;
            if (storage[nowX][nowY] == target) {
                storage[nowX][nowY] = -1;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (!(0 <= nextX && nextX <= N + 1 && 0 <= nextY && nextY <= M + 1)) {
                    continue;
                }
                if (visited[nextX][nextY]) {
                    continue;
                }
                if (storage[nextX][nextY] == -1 || storage[nextX][nextY] == target) {
                    q.offer(new Pos(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}
