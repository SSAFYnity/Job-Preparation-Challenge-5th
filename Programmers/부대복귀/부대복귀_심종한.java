import java.util.*;

class Solution {
    
    private List<Integer>[] edge;
    private int[] dist;
    private int[] answer;    
    private final int INF = Integer.MAX_VALUE;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        answer = new int[sources.length];
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        edge = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            edge[a].add(b);
            edge[b].add(a);
        }
        
        bfs(destination);
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]] == INF ? -1 : dist[sources[i]];
        }
        
        return answer;
    }
    
    private void bfs(int destination) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        dist[destination] = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int next : edge[now]) {
                if (dist[next] == INF) {
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }
    }
}
