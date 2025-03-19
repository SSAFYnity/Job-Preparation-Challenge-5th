import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 홀수일 가능성은 없다
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[2] = 3;
        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;  // 기본 패턴
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;  // 각 특수패턴 2개씩 생긴 것 추가
            }
            dp[i] += 2;  // 해당 패턴에 늘 2개의 특수패턴 생김
        }
        System.out.println(dp[N]);

    }
}
