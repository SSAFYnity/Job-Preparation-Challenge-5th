import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;

        for (int i = 4; i <= N; i++) {
            if (i % 2 == 1)
                continue;

            for (int j = 0; j < i - 2; j = j + 2) {
                dp[i] += dp[j] * 2;
            }
            dp[i] += dp[i - 2] * dp[2];
        }

        System.out.println(dp[N]);
    }
}
