import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(tiling(n));
		sc.close();
	}

	public static int tiling(int n) {
		if (n % 2 == 1) {
			return 0; // 홀수인 경우 타일링 불가능
		}

		int[] dp = new int[n + 1];
		dp[0] = 1;
		if (n >= 2)
			dp[2] = 3;

		for (int i = 4; i <= n; i += 2) {
			dp[i] = dp[i - 2] * 3; // 기본 패턴
			for (int j = 0; j <= i - 4; j += 2) {
				dp[i] += dp[j] * 2;
			}
		}

		return dp[n];
	}

}
