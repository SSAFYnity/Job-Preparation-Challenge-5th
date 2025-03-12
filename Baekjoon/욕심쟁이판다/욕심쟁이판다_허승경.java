/*
 아이디어 : 백트래킹?
 1. 다 돌면서, 해당 기준점으로 돌기
 2. 끝까지 돌면서 최종 값 출력
 */
import java.io.*;
import java.util.*;

public class Main {
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	static int [][] map;
	static int n;
	static int maxCount;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구현
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				backtrack(i, j, map[i][j], 1);	// 현재 행, 열, 현재먹이값, 이동횟수
			}
		}
		
		System.out.println(maxCount);
	}
	
	static void backtrack(int x, int y, long cost, int cnt) {
		
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx >= 0 && tx < n && ty >= 0 && ty < n) {
				if(map[tx][ty] > cost) {
					backtrack(tx, ty, map[tx][ty], cnt+1);
				}
			}
		}
		
		maxCount = Math.max(maxCount,  cnt);
	}
}
