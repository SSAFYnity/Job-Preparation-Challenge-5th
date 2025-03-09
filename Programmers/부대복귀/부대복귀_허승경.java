import java.util.*;

class Point{
    int x;
    int dis;
    
    public Point(int x, int dis){
        this.x = x;
        this.dis = dis;
    }
}

class Solution {
    ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    int [] distance;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
       
        
        // 그래프 정리
        for(int i = 0; i <= n; i++){
            map.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++){
            int a = roads[i][0];
            int b = roads[i][1];
            
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        // i번째 지역 -> 목적지까지 최단 거리 저장
        distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[destination] = 0;  // 목적지 -> 목적지 거리는 0
        
        // 구현
        bfs(destination);   // 목적지 -> 모든 노드
        
        // 정답
        int [] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            if(distance[sources[i]] == Integer.MAX_VALUE){
                answer[i] = -1;
            }else{
                answer[i] = distance[sources[i]];
            }
        }
        
        return answer;
    }
    
    void bfs(int destination){
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(destination, 0));
        
        while(!que.isEmpty()){
            Point p = que.poll();
            
            ArrayList<Integer> tmp = map.get(p.x);
            if(tmp.size() == 0){
                distance[p.x] = -1;
            }else{
                for(int i = 0; i < tmp.size(); i++){
                    if(p.dis+1 < distance[tmp.get(i)]){
                        distance[tmp.get(i)] = p.dis+1;
                        que.add(new Point(tmp.get(i), distance[tmp.get(i)]));
                    }
                }
            }
        }
    }
}