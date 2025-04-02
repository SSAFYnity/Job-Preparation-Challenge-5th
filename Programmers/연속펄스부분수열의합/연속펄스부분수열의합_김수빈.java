import java.util.*;

class Solution {
    private static long kadane(int[] seq) {
        long max = seq[0];
        long curr = seq[0];
        
        for (int i = 1; i < seq.length; i++) {
            curr = Math.max(seq[i], curr + seq[i]);
            max = Math.max(max, curr);
        }
        
        return max;
    }
    
    public long solution(int[] sequence) {
        int[] seq1 = new int[sequence.length];
        int[] seq2 = new int[sequence.length];
        int x = 1;
        for (int i = 0; i < sequence.length; i++) {
            seq1[i] = sequence[i] * x;
            x *= -1;
            seq2[i] = sequence[i] * x;
        }

        long answer = Math.max(kadane(seq1), kadane(seq2));
        return answer;
    }
}
