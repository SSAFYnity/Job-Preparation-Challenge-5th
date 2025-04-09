import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, S;
    private static int[][] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });
        System.out.println(solve());
    }

    private static int solve() {
        int idx = 0, maxValue = 0, ans = 0;
        dp[0] = arr[0][1];
        for (int i = 0; i < N; i++) {
            while (arr[i][0] - arr[idx][0] >= S) {
                maxValue = Math.max(maxValue, dp[idx]);
                idx++;
            }
            dp[i] = arr[i][1] + maxValue;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
