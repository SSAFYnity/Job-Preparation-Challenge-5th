import java.util.*;

class Solution {
    
    int[] stones;
    int K;
    
    public int solution(int[] stones, int k) {
        this.stones = stones;
        this.K = k;
        int answer = 0;
        int l = 1, r = 200_000_000;
        while(l <= r) {
            int mid = (l + r) / 2;
            if (isPossible(mid)) {
                answer = Math.max(answer, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return answer;
    }
    
    private boolean isPossible(int target) {
        int jumpCnt = 0;
        for (int stone : stones) {
            if (stone >= target) {
                jumpCnt = 0;
            } else {
                jumpCnt++;
            }
            if (jumpCnt == K) {
                return false;
            }
        }
        return true;
    }
}
