import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int[][] arr;
    private static int[][] dp;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];
        /* input */
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        /* solve */
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(i, j, arr[i][j]));
            }
        }
        System.out.println(ans);
    }

    private static int dfs(int nowX, int nowY, int before) {
        int now = arr[nowX][nowY];
        if (dp[nowX][nowY] != -1) {
            return dp[nowX][nowY];
        }
        dp[nowX][nowY] = 1;
        for (int i = 0; i < 4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
                if (arr[nextX][nextY] > now) {
                    dp[nowX][nowY] = Math.max(dp[nowX][nowY], dfs(nextX, nextY, now) + 1);
                }
            }
        }
        return dp[nowX][nowY];
    }


}
