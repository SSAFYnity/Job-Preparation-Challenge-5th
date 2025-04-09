import java.util.Arrays;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int len = sequence.length;
        long[] arr1 = new long[len + 1];
        int x = 1;
        long sum1 = 0;
        
        for (int i = 0; i < len; i++) {
            sum1 += sequence[i] * x;
            x *= -1;
            arr1[i + 1] = sum1;
        }
        
        Arrays.sort(arr1);
        answer = Math.abs(arr1[len] - arr1[0]);

        return answer;
    }
}