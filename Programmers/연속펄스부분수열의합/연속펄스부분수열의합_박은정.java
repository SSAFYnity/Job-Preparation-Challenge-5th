import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int[] type1 = new int[sequence.length];
        int[] type2 = new int[sequence.length];
        for(int i = 0; i <  sequence.length; i++){
            if(i % 2 == 0) {
                type1[i] = sequence[i];  
                type2[i] = - sequence[i];
            } else {
                type1[i] = - sequence[i];
                type2[i] = sequence[i];
            }
        }
        long sum = max(type1);
        long sum2 = max(type2);
        answer = Math.max(sum, sum2);
        return answer;
    }
    static long max(int[] arr) {
        long max = arr[0];
        long sum = arr[0];
        for(int i = 1; i < arr.length; i++){
           sum = Math.max(arr[i], sum + arr[i]); // 현재 vs 누적 
           max = Math.max(max, sum);

        }
        return max;
    }
}