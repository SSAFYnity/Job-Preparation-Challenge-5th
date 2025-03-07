import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 우선순위가 높은 작업 먼저 작업 (소요시간 짧은 것, 요청시간 빠른 것, 작업 번호 작은 것 순)
        // 한 번 시작하면 작업 마칠 때까지 그 작업만 수행
        // [작업 요청되는 시점 s, 작업 소요시간 l]

        int n = jobs.length;  // 작업 총 개수
        // 작업 요청되는 시점 기준으로 정렬
        // 요청되는 시점이 같다면 소요시간이 짧은 것부터 우선 정렬
        Arrays.sort(jobs, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int totalRT = 0;  // 총 반환시간
        int idx = 0;  // jobs 인덱스
        int end = 0;  // 끝나는 시간
        int start = -1;  // 시작 시간 초기화

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (p1, p2) -> {
                    if (p1[1] != p2[1]) {
                        return p1[1] - p2[1];
                    } else {
                        return p1[0] - p2[0];
                    }
                }
        );

        while (idx < n || !minHeap.isEmpty()) {
            // 현재 시간 이전에 시작한 모든 작업 힙에 추가
            for (int i = idx; i < n; i++) {
                if (start < jobs[i][0] && jobs[i][0] <= end) {
                    minHeap.add(jobs[i]);
                    idx++;
                } else {
                    break;
                }
            }
            if (!minHeap.isEmpty()) {
                int[] currJob = minHeap.poll();
                start = end;  // 스타트시간 수정
                end += currJob[1];  // 소요시간 더하기
                totalRT += end - currJob[0];  // 반환시간 더하기
            } else {
                end = jobs[idx][0];  // 다음 작업 시작 시간으로 점프
            }
        }


        int answer = totalRT / n;
        return answer;
    }
}
