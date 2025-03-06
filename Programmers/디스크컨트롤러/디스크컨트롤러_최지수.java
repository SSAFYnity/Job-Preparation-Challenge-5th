import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>((o, t) -> {
            if (jobs[o][1] == jobs[t][1]) {
                if (jobs[o][0] == jobs[t][0]) {
                    return o - t;
                } else return jobs[o][0] - jobs[t][0];
            } else return jobs[o][1] - jobs[t][1];
        });
        int t = 0, idx = 0;
        while (idx < jobs.length && jobs[idx][0] <= t) {
            que.add(idx++);
        }
        Arrays.sort(jobs, (o, r) -> o[0]-r[0]);
        while (!que.isEmpty() || idx < jobs.length) {
            if (que.isEmpty()) {
                t = jobs[idx][0];
            } else {
                int now = que.poll();
                t = Math.max(jobs[now][0], t) + jobs[now][1];
                answer += (t - jobs[now][0]);
            }
            
            while (idx < jobs.length && jobs[idx][0] <= t) {
                que.add(idx++);
            }
        }
        return answer / jobs.length;
    }
}