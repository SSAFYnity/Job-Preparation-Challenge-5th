import java.util.*;

class Solution {
    
    char[][] arr = new char[5][5];
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            for (int j = 0; j < 5; j++) {
                arr[j] = place[j].toCharArray();    
            }
            answer[i] = solve();
        }
        return answer;
    }
    
    private int solve() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 'P') {
                    if (!bfs(i, j)) {
                        return 0;   
                    }
                }
            }
        }
        return 1;
    }
    
    private boolean bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        while(!q.isEmpty()) {
            int[] node = q.poll();
            int nowX = node[0];
            int nowY = node[1];
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (!(0 <= nextX && nextX < 5 && 0 <= nextY && nextY < 5)) {
                    continue;
                }
                if (visited[nextX][nextY]) {
                    continue;
                }
                int dist = Math.abs(x - nextX) + Math.abs(y - nextY);
                if (arr[nextX][nextY] == 'O' && dist == 1) {
                    q.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                } else if (arr[nextX][nextY] == 'P' && dist <= 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
