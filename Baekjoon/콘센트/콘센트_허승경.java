import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Integer [] arr = new Integer[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, Collections.reverseOrder());	// 충전 시간 긴 것부터 정렬
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();		// 충전
		
		for(int i = 0; i < n; i++) {
			if(pq.size() < m) {		// 충전 가능
				pq.add(arr[i]);
			}else {
				int tmpTime = pq.poll();
				pq.add(tmpTime + arr[i]);		// 이전 시간 + 현재 시간
				
			}
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			answer = Math.max(answer,  pq.poll());
		}
		
		System.out.println(answer);

	}

}
