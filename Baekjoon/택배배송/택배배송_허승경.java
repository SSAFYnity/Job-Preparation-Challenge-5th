import java.io.*;
import java.util.*;

/*
 * 아이디어: 다익스트라
 * 이유: 간선마다 값이 다르고, 하나의 노드에서 특정노드로 가야함
 * [구현]
 * 1. (num, cost) 원소로하는 Node 클래스 생성
 * 2. 1번에서 i번노드까지 가는데 최소 여물값 저장하는 costArr 생성
 * 3. 1번 노드부터 bfs 돌려~~
 * 4. 현재 노드의 cost + 인접한 노드의 cost가 인접 노드 costArr 보다 작으면 갱신
 * 5. 최종으로는 costArr에서 n번째 인덱스 값 출력
 * */

public class Main {
	static class Node implements Comparable<Node>{
		int num;
		int cost;
		
		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node n) {	// cost 기준 오름차순 정렬
			return this.cost - n.cost;
		}
	}
	static int n, m;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	// 정점 개수
		m = Integer.parseInt(st.nextToken());	// 간선의 개수
		
		for(int i = 0; i < n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));		// 인접 그래프
			graph.get(b).add(new Node(a, c));
		}
		
		// bfs 구현
		int result = dijkstra();
		System.out.println(result);
		
	}
	
	static int dijkstra() {
		int [] costArr = new int[n+1];		// 1-> 각 정점으로의 여물 최솟값 저장 배열
		Arrays.fill(costArr, Integer.MAX_VALUE);	// 초기값(최댓값으로 설정)
		costArr[1] = 0;	// 자기 자신은 0
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			ArrayList<Node> list = graph.get(node.num);
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).cost + costArr[node.num] < costArr[list.get(i).num]) {
					costArr[list.get(i).num] = list.get(i).cost+node.cost;
					pq.add(new Node(list.get(i).num, costArr[list.get(i).num]));
					
				}
			}
		}
		
		return costArr[n];
		
	}
}