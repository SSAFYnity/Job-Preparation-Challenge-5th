import java.io.*;
import java.util.*;;

public class Main {
    static int N;
    static int[][] map = null;
    static int[][] pathCount = null;
    static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pathCount = new int[N][N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int x, int y) {
        if (pathCount[y][x] != 0)
            return pathCount[y][x];

        pathCount[y][x] = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] <= map[y][x])
                continue;

            pathCount[y][x] = Math.max(pathCount[y][x], dfs(nx, ny) + 1);
        }

        return pathCount[y][x];
    }
}
