import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    public int solution(int[][] jobs) {

        int answer = 0;
        int end = 0; // 수행되고 난 직후의 시간
        int jobsIdx = 0; // jobs 배열의 인덱스
        int count = 0; // 수행된 요청 갯수

        // 요청 시간 기준 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 처리 시간 오름차순으로 정렬되는 우선순위 큐(Heap)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 요청이 모두 수행될 때까지 반복
        while (count < jobs.length) {

            // 하나의 작업이 완료되는 시점(end)까지 들어온 모든 요청을 큐에 넣음
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                pq.add(jobs[jobsIdx++]);
            }

            // 큐가 비어 있으면, end를 요청의 가장 처음으로 맞춰줌
            if (pq.isEmpty()) {
                end = jobs[jobsIdx][0];

                // 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행
            } else {
                int[] temp = pq.poll();
                answer += temp[1] + end - temp[0];  // 대기 시간 + 작업 시간
                end += temp[1];  // 작업 완료 후 끝나는 시간 갱신
                count++;
            }
        }

        // 평균 대기 시간 계산
        return answer / jobs.length;
    }
}
