package graph.BFS;

import java.util.*;


public class 아이템줍기_최효빈 {

    boolean[][] visited;
    final int MAX = 1 + 50 * 2 + 1;

    boolean isOuttaBound(int x, int y){
        return x < 0 || y < 0 || x >= MAX || y >= MAX || visited[x][y];
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        int[] octX = { 0, 0,-1, 1,-1,-1, 1,1};
        int[] octY = {-1, 1, 0, 0, 1,-1,-1,1};

        int[] dX = {0,0,-1,1};
        int[] dY = {-1,1,0,0};
        // 상, 하, 좌, 우

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        boolean[][] isArea = new boolean[MAX][MAX];
        boolean[][] isOutline = new boolean[MAX][MAX];


        for(int[] rect : rectangle){
            int startX = rect[0] * 2;
            int startY = rect[1] * 2;
            int endX = rect[2] * 2;
            int endY = rect[3] * 2;

            for(int x = startX; x <= endX; x++){
                for(int y = startY; y <= endY; y++){
                    isArea[x][y] = true;
                }
            }
        }

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0});
        visited = new boolean[MAX][MAX];

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int x = curr[0];
            int y = curr[1];

            int nx, ny;
            for(int dir = 0; dir < 8; dir++){
                nx = x + octX[dir];
                ny = y + octY[dir];

                if(isOuttaBound(nx, ny))
                    continue;

                visited[nx][ny] = true;

                if(isArea[nx][ny])
                    isOutline[nx][ny] = true;
                else
                    que.offer(new int[]{nx, ny});
            }
        }

        que.offer(new int[]{characterX, characterY, 0});
        visited = new boolean[MAX][MAX];
        visited[characterX][characterY] = true;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int x = curr[0];
            int y = curr[1];
            int count = curr[2];

            if(x == itemX && y == itemY){
                return count / 2;
            }

            int nx, ny;
            for(int dir = 0; dir < 4; dir++){
                nx = x + dX[dir];
                ny = y + dY[dir];

                if(isOuttaBound(nx, ny) || !isOutline[nx][ny])
                    continue;

                visited[nx][ny] = true;
                que.offer(new int[]{nx, ny, count+1});
            }
        }


        return 0;
    }

}


