import java.io.*;
import java.util.*;

/*
 * 아이디어 : 구현
 * 1. 머리 이동하기
 * 2-1. 사과 있는 경우 -> 상관 없음
 * 2-2. 사과 없는 경우 -> 꼬리 위치 옮기기
 * 3. 현재 머리 위치 흔적 남기기
 * 4. 머리 방향 조작
 * */

public class Main {
	static class Point{
		int time;
		String ch;
		
		public Point(int time, String ch) {
			this.time = time;
			this.ch = ch;
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int [][] map = new int[n][n];
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 1;	// 사과 표시
		}
		
		Queue<Point> que = new LinkedList<>();
		int x = Integer.parseInt(br.readLine());
		for(int i = 0; i < x; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			
			que.add(new Point(a, c));
		}
		
//		// 정렬 -> time순
//		Collections.sort(list -> (a, b)-> a.time - b.time);
		
		int [] dx = {0, 1, 0, -1};	 // 우, 하, 좌, 상
		int [] dy = {1, 0, -1, 0};
		int dir = 0;	// 방향
		
		Queue<int []> sQue= new LinkedList<>();	// 뱀의 위치들
		sQue.add(new int[] {0, 0});		
		map[0][0] = -1;		// 초기 위치
		int headX = 0;
		int headY = 0;
		
		int answerTime = 0;
		while(true) {
			// 1. 시간 증가
			answerTime++;
						
			// 2. 이동하기
			int tx = headX + dx[dir];
			int ty = headY + dy[dir];
			
			// 3. 종료조건 ( 범위 벗어남 or 자기몸 부딪힘)
			if(tx < 0 || tx >= n || ty < 0 || ty >= n) break;	// 범위 벗어남
			if(map[tx][ty] == -1) break;	//자기 몸 충돌
			
			sQue.add(new int[] {tx, ty});	// 머리 이동
			headX = tx;		// 머리 위치 업데이트
			headY = ty;
			
			// 4. 사과 탐색
			if(map[tx][ty] == 0) {	// 사과가 없을 경우
				int [] tmp = sQue.poll();			// 꼬리 위치 수정
				map[tmp[0]][tmp[1]] = 0;
			}
			
			map[tx][ty] = -1;	// 흔적 남기기
			
			// 5. 방향 변환 확인
			if(!que.isEmpty() && answerTime == que.peek().time) {
				Point p = que.poll();
				if(p.ch.equals("L")) {		// 왼쪽
					if(dir-1 < 0) {
						dir = 3;
					}else {
						dir -= 1;
					}
				}else if(p.ch.equals("D")){	// 오른쪽
					if(dir+1 > 3) {
						dir = 0;
					}else {
						dir += 1;
					}
				}
			}
		}
		
		System.out.println(answerTime);

	}

}
