import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        // 그래프 생성
        ArrayList<Deque<Integer>> q = new ArrayList<>();
        
        for(int i=0 ; i<=n ; i++) {
            q.add(new LinkedList<>());
        }
        
        for(int[] road : roads) {
            q.get(road[0]).add(road[1]);
            q.get(road[1]).add(road[0]);
        }
        
        // 방문체크
        int[] v = new int[n+1];
        v[destination] = 1;  // 시작점

        // 각 위치별 도착시간 처리 bfs
        Deque<Integer> dq = new LinkedList<>();
        dq.add(destination);
        while(!dq.isEmpty()) {
            int poll = dq.poll();
            while(!q.get(poll).isEmpty()) {
                int cur = q.get(poll).poll();
                if (v[cur] == 0) {
                    v[cur] = v[poll] + 1;
                    dq.add(cur);
                }
            }
        }
        
        // 결과값 저장
        for(int i = 0 ; i < sources.length ; i++) {
            answer[i] = v[sources[i]]-1;
        }

        return answer;
    }
}