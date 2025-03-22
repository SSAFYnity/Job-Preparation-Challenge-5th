import java.util.*;

class Solution {
    
    int[][] arr = new int[110][110];
    boolean[][] visited = new boolean[110][110];
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int gx, gy, answer = 0;  
    Set<int[]> set = new HashSet<>();
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        gx = 2 * itemX;
        gy = 2 * itemY;
        for (int[] pos : rectangle) {
            int sx = 2 * pos[0];
            int sy = 2 * pos[1];
            int gx = 2 * pos[2];
            int gy = 2 * pos[3];
            mark(sx, sy, gx, gy);
            set.add(new int[] {sx, sy, gx, gy});
        }
        bfs(2 * characterX, 2 * characterY);
        return answer;
    }
    
    private void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y, 1});
        visited[x][y] = true;
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int nowX = pos[0];
            int nowY = pos[1];
            int cnt = pos[2];
            if (nowX == gx && nowY == gy) {
                answer = cnt / 2;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (inBoundary(nextX, nextY)) {
                    if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        q.offer(new int[] {nextX, nextY, cnt + 1});
                    }  
                }
            }
        }
    }

    private void mark(int sx, int sy, int gx, int gy) {
        for (int i = sx; i <= gx; i++) {
            for (int j = sy; j <= gy; j++) {
                arr[i][j] = 1;
            }
        }
    }
    
    private boolean inBoundary(int x, int y) {
        for (int[] pos : set) {
            if (pos[0] < x && x < pos[2] && pos[1] < y && y < pos[3]) {
                return false;
            }
        }
        return true;
    }
}
