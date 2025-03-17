import java.util.*;

class Solution {
    
    private int N;
    private Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        
    public int solution(int[][] jobs) {
        int answer = 0;
        N = jobs.length;
        Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        return solve(jobs);
    }
    
    private int solve(int[][] jobs) {
        int now = 0;
        int sum = 0;
        int idx = 0;
        int cnt = 0;
        while(cnt < N) {
            while (idx < N && jobs[idx][0] <= now) {
                pq.offer(jobs[idx++]);
            }
            if (!pq.isEmpty()) {
                int[] job = pq.poll();
                now += job[1];
                sum += (now - job[0]);
                cnt++;
            } else {
                now = jobs[idx][0];
            }
        }
        return sum / N;
    }
}
