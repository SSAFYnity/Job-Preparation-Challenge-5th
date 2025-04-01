import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        long sum1 = 0L, sum2 = 0L;
        boolean flag = true;
        for (int num : sequence) {
            int num1 = flag ? num : -num;
            int num2 = flag ? -num : num;
            sum1 = Math.max(sum1 + num1, 0);
            sum2 = Math.max(sum2 + num2, 0);
            answer = Math.max(answer, Math.max(sum1, sum2));
            flag = !flag;
        }
        return answer;
    }
}
