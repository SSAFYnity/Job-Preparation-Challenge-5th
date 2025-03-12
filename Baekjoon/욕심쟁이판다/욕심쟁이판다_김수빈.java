import java.io.*;

public class Main {
    static int n;
    static int[][] bamboos;
    static int[][] dp;
    static int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 재귀구조 이용해서 dfs
    private static int dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];  // 이미 계산된 경우 반환
        }

        dp[r][c] = 1;  // 현재 위치 포함

        for (int[] d : ds) {
            int nr = r + d[0];
            int nc = c + d[1];
            // 범위 안이고 원래 크기보다 커야 이동 가능
            if (nr >= 0 && nr < n && nc >= 0 && nc < n &&
                bamboos[nr][nc] > bamboos[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
        return dp[r][c];
    }
    // 메인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());  // 대나무숲 크기
        bamboos = new int[n][n];  // 대나무숲 정보
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                bamboos[i][j] = Integer.parseInt(line[j]);
                dp[i][j] = -1;
            }
        }
        int maxCnt = 0;  // 최대 칸 이동 수
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                maxCnt = Math.max(maxCnt, dfs(r, c));
            }
        }
        System.out.println(maxCnt);
    }
}
