import java.util.*;

class Solution {
    static int[][] graph = new int[103][103];
    static int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] vector2 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1 ,-1}};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int[] rect : rectangle){
            drawRect(new int[]{rect[0]*2, rect[1]*2, rect[2]*2, rect[3]*2});
        }
        makeRealRect();
        return findItem(characterX*2, characterY*2, itemX*2, itemY*2);
    }

    static int findItem(int x, int y, int itemX, int itemY){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        graph[x][y] = -1;
        int time = 0;
        int size = queue.size();
        while(!queue.isEmpty()){
            for(int i = 0; i < size; i++){
                Point p = queue.poll();
                if(p.x == itemX && p.y == itemY){
                    return time/2;
                }
                for(int[] v: vector){
                    int nx = p.x + v[0];
                    int ny = p.y + v[1];
                    if(!isIn(nx, ny)) continue;
                    if(graph[nx][ny] != 2) continue;
                    graph[nx][ny] = -1;
                    queue.add(new Point(nx, ny));
                }
            }
            time++;
            size = queue.size();
        }
        return -1;
    }

    static void makeRealRect(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        graph[0][0] = -1;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            for(int[] v : vector2){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(!isIn(nx, ny)) continue;
                if(graph[nx][ny] == -1) continue;
                if(graph[nx][ny] == 2) continue;
                if(graph[nx][ny] == 1){
                    graph[nx][ny] = 2;
                    continue;
                }
                queue.add(new Point(nx, ny));
                graph[nx][ny] = -1;
            }
        }
    }

    static boolean isIn(int x, int y){
        return x >= 0 && y >= 0 && x < 103 && y < 103;
    }

    static void drawRect(int[] rectangle){
        for(int i = rectangle[0]; i <= rectangle[2]; i++) {
            graph[i][rectangle[1]] = 1;
            graph[i][rectangle[3]] = 1;
        }
        for(int i = rectangle[1]; i <= rectangle[3]; i++) {
            graph[rectangle[0]][i] = 1;
            graph[rectangle[2]][i] = 1;
        }
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void showGraph(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 52; i++){
            for(int j = 0; j < 52; j++){
                sb.append(graph[i][j]).append("  ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}