import java.util.*;

class Solution {
    public int [][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];    //직사각형을 2배 확장하여 설정

        // 1. 이동 가능한 경로
        for (int[] rect : rectangle) {
            getMap(rect[0] * 2, rect[1] * 2, rect[2] * 2, rect[3] * 2);
        }

        // 2. BFS 최단 거리 구하기 (2배 좌표 적용)
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    void getMap(int sx, int sy, int ex, int ey) {
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if(map[i][j] == 2) continue;
                map[i][j] = 2;
                if(i == sx || i == ex || j == sy || j == ey) map[i][j] = 1; // 테두리
            }
        }
    }

    int bfs(int cx, int cy, int ix, int iy) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{cx, cy});

        int[][] dis = new int[101][101];
        dis[cx][cy] = 0;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!que.isEmpty()) {
            int[] point = que.poll();

            for (int i = 0; i < 4; i++) {
                int tx = point[0] + dx[i];
                int ty = point[1] + dy[i];

                if (isRange(tx, ty) && map[tx][ty] == 1 && dis[tx][ty] == 0) {
                    dis[tx][ty] = dis[point[0]][point[1]] + 1;
                    que.add(new int[]{tx, ty});
                }
            }
        }
        return dis[ix][iy]/2;
    }

    boolean isRange(int x, int y) {
        return x > 0 && x <= 100 && y > 0 && y <= 100;
    }
}
