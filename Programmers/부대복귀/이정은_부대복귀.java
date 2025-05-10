/*
bfs로 돌면서 푼다
*/

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new ArrayList[n+1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        
        q.add(destination);
        visited[destination] = true;
        
        int foundCount = 0;
        
        Map<Integer, Integer> targets = new HashMap<>();
        
        for (int source: sources) {
            targets.put(source, -1);
        }
        
        int step = -1;
q:      while (!q.isEmpty()) {
            int size = q.size();
            step++;
            
            for (int i = 0; i < size; i++) {
                Integer current = q.poll();
                
                if (targets.containsKey(current)) {
                    targets.put(current, step);
                    foundCount++;
                    if (foundCount >= sources.length) {
                        break q;
                    }
                    
                }
                
                // 연결노드 확인
                for (int next: graph[current]) {
                    if (visited[next]) {
                        continue;
                    }
                    
                    visited[next] = true;
                    q.offer(next);
                }
            }
            
        }
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            int current = sources[i];
            answer[i] = targets.get(current);
        }
        
        return answer;
    }
}
