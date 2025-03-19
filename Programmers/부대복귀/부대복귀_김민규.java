import java.util.*;

class Solution {
    static List<List<Integer>> edges;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // roads는 간선 배열
        // sources는 각 부대원이 위치한 지역
        // destination이 복귀지점
        edges = new ArrayList<>();
        
        for(int i=0; i<=n; i++){
            edges.add(new ArrayList<>());
        }
        
        for(int[] road: roads){
            edges.get(road[0]).add(road[1]);
            edges.get(road[1]).add(road[0]);
        }
        
        int[] dist = bfs(n, destination); // 최단거리 배열 반환하기
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
    
    public int[] bfs(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        
        q.add(start); // 역추적쓸거다
        dist[start] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int nxt : edges.get(cur)) {
                if (dist[nxt] == -1) {
                    dist[nxt] = dist[cur] + 1;
                    q.offer(nxt);
                }
            }
        }
        
        return dist;
    }
}