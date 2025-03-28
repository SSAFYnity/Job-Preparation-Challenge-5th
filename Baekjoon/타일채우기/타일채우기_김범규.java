package Baekjoon.타일채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일채우기_김범규 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 홀수는 배치 불가
        if (N % 2 == 1) {
            System.out.println(0);
        } else {
            int[] dp = new int[N + 1];
            dp[0] = 1;
            dp[2] = 3;

            if (N == 2) {
                System.out.println(3);
            } else {
                for (int i = 4; i <= N; i++) {
                    dp[i] = dp[i - 2] * 3;
                    for (int j = i - 4; j >= 0; j -= 2) {
                        dp[i] += (dp[j] * 2);
                    }
                }

                System.out.println(dp[N]);
            }
        }
    }

}
