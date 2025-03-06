import java.util.*;

class Solution {
    class Work implements Comparable<Work> {
        int workTime; // 소요 시간
        int inputTime; // 요청 시각
        int no; // 작업 번호
        
        Work(int workTime, int inputTime, int no) {
            this.workTime = workTime;
            this.inputTime = inputTime;
            this.no = no;
        }
        
        @Override
        public int compareTo (Work o) {
            int[] compareValues = new int[] {this.workTime - o.workTime, this.inputTime - o.inputTime, this.no - o.no};
            
            for(int value: compareValues) {
                if (value == 0) {
                    continue;
                }
                return value;
            }
            
            return 0;
        }
    }
    
    public int solution(int[][] jobs) {
        
        PriorityQueue<Work> pq = new PriorityQueue<>();
        
        int i = 0;
        
        int no = i;
        int inputTime = jobs[i][0];
        int workTime = jobs[i][1];
        
        int currentTime = 0;
        int sum = 0;
        
        while (i < jobs.length) {
            
            // 작업 넣기
            while (i < jobs.length && inputTime <= currentTime) {
                pq.add(new Work(workTime, inputTime, no));
                i++;
                if (i < jobs.length) {
                    no = i;
                    inputTime = jobs[i][0];
                    workTime = jobs[i][1];
                }
            }
            
            // 작업하기
            Work w = pq.poll();
            currentTime += w.workTime;
            
            sum += (currentTime - w.inputTime);
            
            
            
        }
        
        int answer = sum / jobs.length;
        return answer;
    }
}
