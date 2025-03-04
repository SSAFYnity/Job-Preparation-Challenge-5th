import java.util.*;

public class Solution {
    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[destination] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, destination});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dist = cur[0], node = cur[1];

            if (dist > distances[node]) continue;

            for (int neighbor : graph.get(node)) {
                int newDist = dist + 1;
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    pq.offer(new int[]{newDist, neighbor});
                }
            }
        }

        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = (distances[sources[i]] == Integer.MAX_VALUE) ? -1 : distances[sources[i]];
        }

        return result;
    }
}
