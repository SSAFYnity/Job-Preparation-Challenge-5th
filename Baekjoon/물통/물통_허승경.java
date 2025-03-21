/*
 * 아이디어 : bfs
 * */

import java.io.*;
import java.util.*;

public class Main {
	static boolean [][] visited = new boolean[201][201];		// A물병에 a만큼, B물병에 b만큼 있는 상태
	static boolean [] answer = new boolean[201]; 
	static int [] arr;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 탐색
		bfs();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 201; i++) {
			if(answer[i]) sb.append(i+ " ");
		}
		
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		Queue<int []> que = new LinkedList<>();
		que.add(new int[] {0, 0, arr[2]});	// 초기화
		visited[0][0] = true;
		
		while (!que.isEmpty()) {
            int[] cur = que.poll();
            int a = cur[0];		//a
            int b = cur[1];		//b
            int c = cur[2];		//c

            if (a == 0) answer[c] = true;	// a비워진 상태 -> c값 확인

            // 6가지 물 붓기 시도
            for (int i = 0; i < 3; i++) {		// (i -> j)로 물 붓기
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;

                    int[] next = {a, b, c};
                    int amount = Math.min(next[i], arr[j] - next[j]);

                    next[i] -= amount;		// 시작 물병 (물 감소)
                    next[j] += amount;		// 도착 물병 (물 추가)

                    if (!visited[next[0]][next[1]]) {
                        visited[next[0]][next[1]] = true;		// a물병에 next[0]만큼, b물병에 next[1]만큼 있음
                        que.add(new int[]{next[0], next[1], next[2]});
                    }
                }
            }
        }
		
		
	}

}
