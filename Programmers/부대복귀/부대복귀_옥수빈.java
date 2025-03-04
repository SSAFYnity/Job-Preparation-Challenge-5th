import java.util.*;
import java.awt.Point;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        Map<Integer, ArrayList<Integer>> connectList = new HashMap<>();
        for(int[] road : roads){
            if(!connectList.containsKey(road[0]))
                connectList.put(road[0], new ArrayList<>(Arrays.asList(road[1])));
            else
                connectList.get(road[0]).add(road[1]);
           if(!connectList.containsKey(road[1]))
                connectList.put(road[1], new ArrayList<>(Arrays.asList(road[0])));
            else
                connectList.get(road[1]).add(road[0]);
        }
                
        int[] cost = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[destination] = 0;
        visited[destination] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(destination, 0));
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int s = 0; s < size; s++){
                Point p = queue.poll();
                for(int next : connectList.get(p.x)){
                    if(!visited[next]){
                        cost[next] = p.y + 1;
                        queue.add(new Point(next, p.y + 1));
                        visited[next] = true;
                    }
                }
            }
        }
                
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = cost[sources[i]] == Integer.MAX_VALUE ? -1 : cost[sources[i]];
        }
        
        return answer;
    }
}