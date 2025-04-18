import java.io.*;
import java.util.*;

public class Main {
	static int maxCost;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			List<List<int[]>> list = new ArrayList<>();		// 정보 저장
			for(int i = 0; i < 11; i++) {
				list.add(new ArrayList<>());
			}
			
			for(int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 11; j++) {
					int cost = Integer.parseInt(st.nextToken());
					if(cost != 0) {
						list.get(i).add(new int[] {j, cost});		// 포지션 위치, 능력치						
					}
				}
			}
			
			maxCost = 0;
			backtrack(list, new boolean[11], 0, 0);
			sb.append(maxCost+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void backtrack(List<List<int[]>> list, boolean [] visited, int cost, int index) {
		// 가지치기
		if(index == 11) {
			maxCost = Math.max(maxCost, cost);
			return;
		}
		
		for(int i = 0; i < list.get(index).size(); i++) {
			int position = list.get(index).get(i)[0];
			int curCost = list.get(index).get(i)[1];
			
			if(!visited[position]) {
				visited[position] = true;
				backtrack(list, visited, cost+curCost, index+1);
				visited[position] = false;
			}
		}
		
		return;
	}

}
