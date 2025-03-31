import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int T, ans;
    private static int[][] arr;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        arr = new int[11][11];
        while (T-- > 0) {
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = 0;
            dfs(0, 0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int sum, int bit) {
        if (depth == 11) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (arr[depth][i] == 0) {
                continue;
            }
            if ((bit & 1 << i) != 0) {
                continue;
            }
            dfs(depth + 1, sum + arr[depth][i], bit | 1 << i);
        }
    }
}
