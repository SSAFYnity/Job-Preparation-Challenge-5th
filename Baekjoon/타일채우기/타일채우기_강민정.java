import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 가로 크기 <= 30
        int[] dp = new int[n + 1];

        if(n % 2 == 1) {        // 가로 길이가 홀수면 타일을 채울 수 없음
            System.out.print(0);
            return;
        }

        dp[0] = 1;      // 아무것도 고르지 않는 것이 경우의 수 1가지
        dp[2] = 3;

        for(int i=4; i<=n; i+=2) {
            dp[i] += dp[i - 2] * 3;
            for(int j=i-4; j>=0; j-=2) {
                dp[i] += dp[j] * 2;
            }
        }
        System.out.print(dp[n]);
    }
}