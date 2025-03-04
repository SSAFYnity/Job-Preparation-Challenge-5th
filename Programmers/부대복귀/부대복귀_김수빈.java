package unsolved;
import java.util.*;

public class 부대복귀_김수빈            {
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 인접리스트
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        // 인접리스트 표시
        for (int[] road : roads) {
            adjList.get(road[0]).add(road[1]);
            adjList.get(road[1]).add(road[0]);
        }
        // 도착지에서 모든 노드까지 최단 거리 한번만 구하기
        int[] minDist = bfs(destination, n, adjList);
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = minDist[sources[i]];
        }

        return answer;
    }

    public static int[] bfs(int d, int n, List<List<Integer>> adjList) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);  // 일단 모든 노드를 -1로 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(d);  // 시작 위치 목적지로
        dist[d] = 0;  // 시작 위치 0

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adjList.get(curr)) {
                // 방문하지 않았다면
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int[] result = solution(5,
                new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}},
                new int[] {1, 3, 5},
                5);

        System.out.println(Arrays.toString(result));
    }
}
