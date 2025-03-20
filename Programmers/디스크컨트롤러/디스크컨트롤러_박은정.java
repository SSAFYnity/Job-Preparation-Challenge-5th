import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(x -> x[0])); // 요청 시각 순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[1])); // 소요 시간 짧은 순
        
        int curTime = 0, sumReturnTime = 0, jIdx = 0, finishedCnt = 0, n = jobs.length;
        
        while (finishedCnt < n) {
            while (jIdx < n && jobs[jIdx][0] <= curTime) {
                pq.add(jobs[jIdx++]); // 요청 시간이 현재 시간 이하인 작업 추가
            }
            
            if (!pq.isEmpty()) {
                int[] curJob = pq.poll();
                curTime += curJob[1]; // 소요 시간 추가
                sumReturnTime += (curTime - curJob[0]); // 요청부터 완료까지 걸린 시간
                finishedCnt++;
            } else {
                curTime = jobs[jIdx][0]; // 다음 요청 시간으로 건너뜀
            }
        }
        
        return sumReturnTime / n;
    }
}
