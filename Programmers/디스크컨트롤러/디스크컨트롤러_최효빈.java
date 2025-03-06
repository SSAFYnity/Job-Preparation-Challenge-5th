package comparator;

import java.util.*;

class Job {
    int id;
    int requestTime;
    int duration;

    public Job(int id, int requestTime, int duration){
        this.id = id;
        this.requestTime = requestTime;
        this.duration = duration;
    }
}


public class 디스크컨트롤러_최효빈 {

    final int MAX = Integer.MAX_VALUE;

    public int solution(int[][] jobs) {

        PriorityQueue<Job> requestPQ = new PriorityQueue<>(
                (j1, j2) -> compareJobs(j1, j2)
        );

        Queue<Job> suspendQue = new ArrayDeque<>();

        for(int i = 0; i < jobs.length; i++){
            requestPQ.offer(new Job(i, jobs[i][0], jobs[i][1]));
        }

        int elapsedTime = 0;
        int sum = 0;

        while(!requestPQ.isEmpty()){
            Job curr = requestPQ.poll();

            int minSuspendTime = MAX;

            while(curr != null && curr.requestTime > elapsedTime){
                suspendQue.offer(curr);
                minSuspendTime = Math.min(curr.requestTime, minSuspendTime);
                curr = requestPQ.poll();
            }

            if(curr != null){
                elapsedTime += curr.duration;
                sum += elapsedTime - curr.requestTime;
            }

            while(!requestPQ.isEmpty()){
                Job unfinishedJob = requestPQ.poll();
                minSuspendTime = Math.min(unfinishedJob.requestTime, minSuspendTime);
                suspendQue.offer(unfinishedJob);
            }

            if(minSuspendTime < MAX)
                elapsedTime = Math.max(minSuspendTime, elapsedTime);


            while(!suspendQue.isEmpty()){
                requestPQ.offer(suspendQue.poll());
            }
        }

        return (int) Math.floor(sum / jobs.length);
    }

    int compareJobs(Job j1, Job j2){

        int durationGap = j1.duration - j2.duration;
        if(durationGap != 0)
            return durationGap;

        int requestTimeGap = j1.requestTime - j2.requestTime;
        if(requestTimeGap != 0)
            return requestTimeGap;

        return j1.id - j2.id;
    }

}