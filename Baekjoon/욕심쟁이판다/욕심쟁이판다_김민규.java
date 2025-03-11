package boj;

import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map;
	static int[][] dp;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 }; // 4방

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		dp = new int[n][n];
		for (int[] r : dp) {
			Arrays.fill(r, -1); // 방문안한걸 -1로 하기로 함
		}
		int max = -1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 받기 끝
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				go(i,j);
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);
		
//		for(int[] r: dp) {
//			System.out.println(Arrays.toString(r));
//		}
	}

	public static int go(int ci, int cj) {
		if (dp[ci][cj] != -1)
			return dp[ci][cj]; // 이미 더 긴 값이 존재하면 끊어버리기

		dp[ci][cj] = 1;
		
		for (int i = 0; i < 4; i++) {
			int ni = ci + di[i];
			int nj = cj + dj[i];
			if (isValid(ni, nj) && map[ni][nj] > map[ci][cj]) {
				// 유효한 길이고 대나무가 더 많으면
				dp[ci][cj] = Math.max(dp[ci][cj], go(ni, nj) + 1);
			}
		}

		return dp[ci][cj];
	}

	public static boolean isValid(int ci, int cj) {
		// 갈 수 있는지
		return ci < n && cj < n && ci >= 0 && cj >= 0;
	}
}
