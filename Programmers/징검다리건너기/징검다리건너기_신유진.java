import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int left = 1, right = 200000000, answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 건널 수 있는 사람 수

            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;  // 더 많은 사람이 건널 수 있는지 확인
            } else {
                right = mid - 1; // 건널 수 없다면 인원 수 줄이기
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int mid) {
        int skip = 0; // 연속된 0의 개수

        for (int stone : stones) {
            if (stone < mid) {  // 해당 징검다리는 mid명이 밟을 수 없을 경우
                skip++;
                if (skip >= k) return false; // 연속된 0이 k개 이상이면 불가능
            } else {
                skip = 0; // 다시 밟을 수 있는 돌이면 초기화
            }
        }

        return true;
    }
}
