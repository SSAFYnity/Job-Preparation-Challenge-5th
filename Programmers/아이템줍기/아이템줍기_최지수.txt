import java.util.*;

class Solution {
    boolean[][] visit = new boolean[102][102];
    int[][] dr = {{1,-1,0,0}, {0,0,1,-1}}, arr = new int[102][102];
    class WV {
        int y, x, cnt;
        public WV(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
    boolean isBorder(int sy, int sx, int ey, int ex, int y, int x) {
        return y == sy || y == ey || x == sx || x == ex;
    }
    void draw(int sy, int sx, int ey, int ex) {
        for (int i = sy; i <= ey; i++) {
            for (int j = sx; j <=ex; j++) {
                if (arr[i][j] == 2) continue;
                arr[i][j] = (isBorder(sy, sx, ey, ex, i, j) ? 1 : 2);
            }
        }
    }
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = Integer.MAX_VALUE;
        
        for (int[] i : rectangle) {
            draw(i[1]*2, i[0]*2, i[3]*2, i[2]*2);
        }
        
        PriorityQueue<WV> que = new PriorityQueue<>((o, t) -> o.cnt - t.cnt);
        que.add(new WV(characterY*2, characterX*2, 0));
        visit[characterY*2][characterX*2] = true;
        
        while (!que.isEmpty()) {
            WV now = que.poll();
            if (now.y == itemY*2 && now.x == itemX*2) {
                answer = now.cnt;
                break;
            }
            for (int d = 0; d < 4; d++) {
                int y = now.y + dr[0][d];
                int x = now.x + dr[1][d];
                if (arr[y][x] != 1 || visit[y][x]) continue;
                que.add(new WV(y,x,now.cnt+1));
                visit[y][x] = true;
            }
        }
        return answer/2;
    }
}