import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        int n = sequence.length;
        long[] arr = new long[n+2];
        long[] mini = new long[n+2];
        long[] maxi = new long[n+2];
        
        arr[2] = sequence[0];
        mini[2] = Math.min(0, sequence[0]);
        maxi[2] = Math.max(0, sequence[0]);
        if (n >= 2) {
            arr[3] = sequence[0] - sequence[1];
            mini[3] = Math.min(mini[2], arr[3]);
            maxi[3] = Math.max(maxi[2], arr[3]);
        }
        
        for (int i = 2; i < n; i++) {
            arr[i+2] = arr[i+1] + sequence[i] * (i%2==0 ? 1 : -1);
            mini[i+2] = Math.min(mini[i+1], arr[i+2]);
            maxi[i+2] = Math.max(maxi[i+1], arr[i+2]);
        }
        
        for (int i = 2; i < n+2; i++) {
            answer = Math.max(answer, Math.abs(arr[i] - mini[i-1]));
            answer = Math.max(answer, Math.abs(arr[i] - maxi[i-1]));
        }
        return answer;
    }
}