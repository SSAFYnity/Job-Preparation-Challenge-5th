package mar2025;

/*
 아이디어 : dp + dfs
 1. dp[i][j] -> (i, j)에서 시작했을 때, 이동할 수 있는 최대 경로
 2. 처음부터 다 돌면서 모든 dp값 갱신하기
 3. 돌면서 이미 방문한 곳 있으면 더이상 탐색하지 않고 dp[i][j]반환
 4. 끝까지 돌면서 최종 값 출력
 */

import java.io.*;
import java.util.*;

public class BOJ_0312_1937 {
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	static int [][] map;
	static int [][] dp;
	static int n;
	static int maxCount;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		dp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구현
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				maxCount = Math.max(maxCount, backtrack(i, j));	// 현재 행, 열
			}
		}
		
		System.out.println(maxCount);
	}
	
	static int backtrack(int x, int y) {
		if(dp[x][y] != 0) {
			return dp[x][y];		// 이미 탐색한 곳 -> dp값 반환
		}
		
		dp[x][y] = 1;	// 자기 자신은 포함
		
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx >= 0 && tx < n && ty >= 0 && ty < n) {
				if(map[tx][ty] > map[x][y]) {
					dp[x][y] = Math.max(dp[x][y], backtrack(tx, ty)+1);
				}
			}
		}
		
		return dp[x][y];
	}
}
