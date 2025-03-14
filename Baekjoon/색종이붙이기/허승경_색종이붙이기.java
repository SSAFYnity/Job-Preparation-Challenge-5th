import java.io.*;
import java.util.*;

/*
 * 문제 이해
 * -> 정사각형 색종이 최소한의 횟수로 써서 1덮기.
 * 
 * 유형: 백트래킹 + 그리디
 * 잘못된 아이디어: bfs/dfs 및 사방탐색 -> 그래프라고 무조건 bfs, dfs, 사방탐색 아님!
 * -> 해당 문제는 "정사각형"으로 크게크게 해야함 -> 4방탐색은 모양을 휘갈기게 함!
 * 
 * 접근 방법
 * 1. 현재 위치가 1일 때, 해당 위치를 색종이의 왼쪽 상단으로 잡는다.
 * 2. 가장 큰 색종이의 크기부터 붙일수 있는지 확인한다.
 * 3. 현재 위치 기준으로 map상의 색종이 다 붙이면 그때의 최솟값 산정
 * 4. 현재 위치에서 전부 다 붙인 색종이 복구하고 다음 위치로 진행
 * 5. 1~4 반복
 * 6. 반복후 정답 출력
 * */

public class Main {
	static int [][] map = new int[10][10];
	static int [] paperCount = {0, 5, 5, 5, 5, 5};	// 1*1 ~ 5*5 까지 각 5장씩
	static int minCount = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtrack(0);
		
		// 출력
		if(minCount == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minCount);
		
		// 구현
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {	// 붙여야하는 위치
					
					
				}
			}
		}

	}
	
	static boolean isCovered() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {	// 아직 남아있음.
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean checkSize(int x, int y, int size) {
		if(x+size > 10 || y+size > 10) return false;	// 범위 해당 X
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[x+i][y+j] != 1) return false;			// 색종이 부착 가능한 위치
			}
		}
		
		return true;
	}
	
	static void attachPaper(int x, int y, int size, int status) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[x+i][y+j] = status;
			}
		}
	}
	
	static void backtrack(int count){
		// 백트래킹 가지치기
		if(isCovered()) {	// 모든 1위치에 색종이 부착 확인
			minCount = Integer.min(minCount,  count);	// 더 작은 횟수로 갱신
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {	// 색종이 부착 위치
					for(int size = 5; size > 0; size--) {		// 제일 큰 색종이 크기부터 시작  = 5
						if(paperCount[size] > 0 && checkSize(i, j, size)) {	// 해당 size 색종이 개수 남음  + 딱맞는 사이즈 확인
							attachPaper(i, j, size, 0);	// 해당 위치에 부착
							paperCount[size]--;	// 해당 색종이 개수 차감
							backtrack(count+1);	// 종이수 증가 후 백트래킹
							attachPaper(i, j, size, 1);		// 붙인 색종이 제거
							paperCount[size]++;		// 차감된 색종이 개수 증가
						}
					}
					return;	
				}
			}
		}
	}
}
