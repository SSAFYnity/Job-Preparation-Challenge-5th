import java.util.*;

class Solution {
    class Point implements Comparable<Point> {
        int startAt;
        int time;
        
        public Point(int startAt, int time){
            this.startAt = startAt;
            this.time = time;
        }
        
        @Override
        public int compareTo(Point p){
            // 우선순위 소요시간 -> 요청 시각
            if(this.time == p.time) return this.startAt - p.startAt;
            return this.time - p.time;
        }
        
        
    }
    public int solution(int[][] jobs) {
        // 1. 요청시각 기준 오름차순 정렬
        Arrays.sort(jobs, (a, b) -> a[0]-b[0]);
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        int n = jobs.length;
        int idx = 0;      
        int endAt = 0;    // 현재 시간
        int total = 0;    // 총 대기시간
        int count = 0;    // 완료된 작업 수
        
        while(count < n){
            // 현재 시간 이전에 요청된 작업들 pq에 추가
            while(idx < n && jobs[idx][0] <= endAt){
                pq.add(new Point(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if(pq.isEmpty()){
                // 큐가 비어있을 경우 -> 다른 요청 들어오는 시간으로 갱신
                endAt = jobs[idx][0];
            }else{
                // pq에서 실행시간 가장 짧은 작업 수행
                Point p = pq.poll();
                endAt += p.time;    // 작업 끝나는 시간 갱신
                total += (endAt - p.startAt);       //요청 시간 부터 종료까지 걸린 시간
                count++;
            }
        }
        
        return total/n;
    }
}