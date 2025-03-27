import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 헛간의 개수 소들의 길(양방향)
	static List<Node> adj[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			// A <-> B, C마리의 소
			adj[A].add(new Node(B, C));
			adj[B].add(new Node(A, C));
		}
		// 1 -> N 최단 경로
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		dist[1] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			for (Node next : adj[now.to]) {

				if (dist[next.to] > dist[now.to] + next.cost) { // 더 좋은 경로가 나중에 나올 수도 있음 따라서 visited 사용 x
					dist[next.to] = dist[now.to] + next.cost;
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
		System.out.println(dist[N]);
	}

	static class Node implements Comparable<Node> {

		int to, cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {

			return this.cost - o.cost;
		}
	}

}
