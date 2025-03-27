import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        ArrayList<Integer>[] map = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            map[road[0]].add(road[1]);
            map[road[1]].add(road[0]);
        }
        
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int[] visit = new int[n+1];
        Arrays.fill(visit, -1);
        visit[destination] = 0;
        que.add(destination);
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i : map[now]) {
                if (visit[i] != -1) continue;
                visit[i] = visit[now]+1;
                que.add(i);
            }
        }
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = visit[sources[i]];
        }
        
        return answer;
    }
}