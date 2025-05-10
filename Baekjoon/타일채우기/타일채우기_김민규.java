import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 1]; // | 아니면 ㅡ
        System.out.println(go());
    }

    public static int go() {
        // dp[i][j] 에는, 너비가 1 일때
        if (n % 2 != 0) return 0;
        if (n == 2) return 3;
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 2] * 3;

            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2; // dp[4] 에서 새로운 패턴이 있음
            }
        }
        return dp[n];
    }
}