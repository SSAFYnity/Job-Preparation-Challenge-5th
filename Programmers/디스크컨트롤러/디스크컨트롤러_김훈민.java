import java.util.*;

class Solution {
    PriorityQueue<Job> pq = new PriorityQueue<>();
    public int solution(int[][] jobs) {
        int cnt = 0;
        Job[] jobss = new Job[jobs.length];
        for(int [] job : jobs){
            jobss[cnt] = new Job(cnt++, job[1], job[0]);
        }

        Arrays.sort(jobss, (o1, o2) -> o1.startTime - o2.startTime);

        cnt = 0;
        int allTaskTime = 0;
        int nowTime = 0;

        while(cnt < jobss.length || !pq.isEmpty()){
            while(cnt < jobss.length && jobss[cnt].startTime <= nowTime){
                pq.add(jobss[cnt++]);
            }

            if(!pq.isEmpty()) {
                Job nowJob = pq.poll();
                nowTime += nowJob.taskTime;
                allTaskTime += (nowTime - nowJob.startTime);
            } else {
                if(cnt < jobss.length) nowTime = jobss[cnt].startTime;
            }
        }
        return allTaskTime / jobss.length;
    }
}

class Job implements Comparable<Job>{
    int idx;
    int taskTime;
    int startTime;

    Job(int idx, int taskTime, int startTime){
        this.idx = idx;
        this.taskTime = taskTime;
        this.startTime = startTime;
    }

    public int compareTo(Job o){
        if(this.taskTime == o.taskTime && this.startTime == o.startTime) return this.idx - o.idx;
        if(this.taskTime == o.taskTime) return this.startTime - o.startTime;
        return this.taskTime - o.taskTime;
    }
}