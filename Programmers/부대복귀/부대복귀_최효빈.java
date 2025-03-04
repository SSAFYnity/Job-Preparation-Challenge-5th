package graph.BFS;

import java.util.*;

public class Programmers_132266 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        final int MAX = Integer.MAX_VALUE;
      
        int[] minDist = new int[n+1];
        Arrays.fill(minDist, MAX);

        List<Integer>[] adjList = new List[n+1];
        for(int i = 1; i <= n; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int[] road : roads){
            adjList[road[0]].add(road[1]);
            adjList[road[1]].add(road[0]);
        }

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{destination, 0});

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int pos = curr[0];
            int dist = curr[1];

            minDist[pos] = Math.min(dist, minDist[pos]);

            for(int idx = 0; idx < adjList[pos].size(); idx++){
                int nextPos = adjList[pos].get(idx);
                if(minDist[nextPos] != MAX)
                    continue;
                que.offer(new int[]{nextPos, dist + 1});
            }
        }

        int[] answer = new int[sources.length];
        for(int idx = 0; idx < sources.length; idx++){
            int result = minDist[sources[idx]];
            answer[idx] = result == MAX ? -1 : result;
        }
        return answer;
    }
}
