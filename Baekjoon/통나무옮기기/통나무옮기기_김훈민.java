import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] graph;
    static boolean[][][] visited;
    static Point[] tree = new Point[3];
    static Point[] ends = new Point[3];
    static int tree_dir;
    static int ends_dir;
    static Queue<P_Point> queue = new LinkedList<>();
    static int result = Integer.MAX_VALUE;
    static int[][] vector = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        visited = new boolean[2][N][N];
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                if(graph[i][j] == 'B') {
                    tree[count1++] = new Point(i, j);
                }
                if(graph[i][j] == 'E') {
                    ends[count2++] = new Point(i, j);
                }
            }
        }
        if(tree[0].y == tree[1].y) tree_dir = 1;
        if(ends[0].y == ends[1].y) ends_dir = 1;
        queue.add(new P_Point(tree[1].x, tree[1].y, tree_dir, 0));
        visited[tree_dir][tree[1].x][tree[1].y] = true;
        bfs();
        if(result == Integer.MAX_VALUE) {
            result = 0;
        }
        System.out.println(result);

    }

    static void bfs() {
        while(!queue.isEmpty()){
            P_Point p = queue.poll();
            if(p.x == ends[1].x && p.y == ends[1].y && p.dir == ends_dir) {
                result = Math.min(result, p.count);
                continue;
            }
            for(int[] v : vector) {
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(graph[nx][ny] == '1') continue;
                if(isIn(nx, ny, p.dir) && !visited[p.dir][nx][ny]) {
                    queue.add(new P_Point(nx, ny, p.dir, p.count + 1));
                    visited[p.dir][nx][ny] = true;
                }
            }
            int n_dir = (p.dir + 1) % 2;
            if(canRotate(p.x, p.y) && !visited[n_dir][p.x][p.y]) {
                queue.add(new P_Point(p.x, p.y, n_dir, p.count + 1));
                visited[n_dir][p.x][p.y] = true;
            }
        }
    }

    static boolean isIn(int x, int y, int dir) {
        if(dir == 0) {
            if(y - 1 < 0 || y + 1 >= N || graph[x][y - 1] == '1' || graph[x][y + 1] == '1') {
                return false;
            }
        }
        else {
            if(x - 1 < 0 || x + 1 >= N || graph[x - 1][y] == '1' || graph[x + 1][y] == '1') {
                return false;
            }
        }
        return true;
    }

    static boolean canRotate(int x, int y) {
        for(int i = x-1; i <= x+1; i++) {
            for(int j = y-1; j <= y+1; j++) {
                if(i<0 || i>=N || j<0 || j>=N || graph[i][j] == '1') return false;
            }
        }
        return true;
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class P_Point extends Point{
        int dir;
        int count;

        P_Point(int x, int y, int dir, int count){
            super(x, y);
            this.dir = dir;
            this.count = count;
        }
    }

}