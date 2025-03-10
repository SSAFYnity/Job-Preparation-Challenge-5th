import java.util.*;

class Solution {
    static int[] visited;
    static ArrayList<Integer>[] graph;
    static int dest;
    static Queue<Integer> queue = new LinkedList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        graph = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++){
            graph[i] = new ArrayList();
        }
        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        bfs(destination);
        int[] answer = new int[sources.length];
        int cnt = 0;
        for(int source : sources){
            if(visited[source] == Integer.MAX_VALUE) answer[cnt++] = -1;
            else answer[cnt++] = visited[source];
        }
        return answer;
    }

    static void bfs(int start){
        queue.add(start);
        visited[start] = 0;
        int size = queue.size();
        int time = 1;
        while(!queue.isEmpty()){
            for(int i = 0; i < size; i++){
                int cur = queue.poll();
                for(int next : graph[cur]){
                    if(visited[next] != Integer.MAX_VALUE) continue;
                    visited[next] = time;
                    queue.add(next);
                }
            }
            size = queue.size();
            time++;
        }
    }
}