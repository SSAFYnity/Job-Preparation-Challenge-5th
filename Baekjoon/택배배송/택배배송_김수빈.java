import java.io.*;
import java.util.*;

public class Main {
    private static int dijkstra(List<List<int[]>> adjList, int N) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;  // 출발지

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});  // 시작 노드, 누적 비용
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int now = curr[0];
            int cost = curr[1];

            if (cost > dist[now]) {
                continue;
            }

            for (int[] edge : adjList.get(now)) {
                int next = edge[0];
                int newCost = cost + edge[1];
                if (newCost < dist[next]) {
                    dist[next] = newCost;
                    pq.add(new int[]{next, newCost});
                }
            }
        }
        return dist[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);  // 헛간 개수
        int M = Integer.parseInt(input[1]);  // 서로 이동 가능한 헛간 개수
        List<List<int[]>> adjList = new ArrayList<>();  // 인접리스트
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            String[] infos = br.readLine().split(" ");
            int A = Integer.parseInt(infos[0]);
            int B = Integer.parseInt(infos[1]);
            int C = Integer.parseInt(infos[2]);
            // 헛간 사이 소 마리 수 집어넣기
            adjList.get(A).add(new int[] {B, C});
            adjList.get(B).add(new int[] {A, C});
        }
        boolean[] visited = new boolean[N + 1];
        int answer = dijkstra(adjList, N);
        System.out.println(answer);
    }
}
