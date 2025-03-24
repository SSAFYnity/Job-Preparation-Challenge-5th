import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int result = 1;
    static int[][] graph;
    static int[][] dp;
    static int[][] inDegree;
    static int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][N];
        inDegree = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int[] v : vector){
                    int nx = i + v[0];
                    int ny = j + v[1];
                    if(isIn(nx, ny)){
                        if(graph[nx][ny] < graph[i][j]){
                            inDegree[i][j]++;
                        }
                    }
                }
            }
        }

        for(int x = 0; x < N; x++){
            for(int y = 0; y < N; y++){
                if(inDegree[x][y] == 0){
                    dp[x][y] = 1;
                    queue.add(new Point(x, y));
                }
            }
        }

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            for(int[] v : vector){
                int nx = cur.x + v[0];
                int ny = cur.y + v[1];
                if(isIn(nx, ny) && graph[nx][ny] > graph[cur.x][cur.y]){
                    dp[nx][ny] = Math.max(dp[nx][ny], dp[cur.x][cur.y] + 1);
                    result = Math.max(result, dp[nx][ny]);
                    inDegree[nx][ny]--;
                    if(inDegree[nx][ny] == 0){
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }

        System.out.println(result);
    }

    static boolean isIn(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
