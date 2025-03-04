import java.util.*;
class Solution {
    static int[] dist;
    static List<Node>adj[];
    static PriorityQueue<Node> pq; 
    // 총 지역의 수 | 왕복 길 정보들 | 서로 다른 지역들 | 강철부대 지역 
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        adj = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            adj[road[0] - 1].add(new Node(road[1] - 1, 1));
            adj[road[1] - 1].add(new Node(road[0] - 1, 1));
        }
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // destination으로부터 거리

        pq = new PriorityQueue<>();
        pq.add(new Node(destination - 1, 0));
        dist[destination - 1] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(dist[now.id] < now.cost) continue; // 과거 가장 최소라고 했던 비용 < 현재 비용
            for(Node next : adj[now.id]) { // 업데이트 될 여지가 있는 후보들 
                int newDist = dist[now.id] + next.cost; // now.id -> next.id 이동하는 새로운 비용
                if(newDist < dist[next.id]) { 
                    dist[next.id] = newDist; // 비용 갱신 
                    pq.add(new Node(next.id, newDist));
                }
            }
        }
        
        int[] ans = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            ans[i] = dist[sources[i] - 1] == Integer.MAX_VALUE ? -1 : dist[sources[i] - 1];
        }
        return ans;
    }
    static class Node implements Comparable<Node> {
        int id, cost;
        Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}