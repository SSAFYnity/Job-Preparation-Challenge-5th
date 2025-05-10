class Solution {
    public long solution(int[] sequence) {
        long[] acc = new long[sequence.length];
        acc[0] = sequence[0];
        
        for(int i = 1; i < sequence.length; i++){
            acc[i] = acc[i-1] + sequence[i] * (i % 2 == 0 ? 1 : -1);
        }
        
        long answer = Math.max(acc[0], -acc[0]);
        
        for(int i = 1; i < sequence.length; i++){
            answer = Math.max(answer, Math.max(acc[i], -acc[i]));
            for(int j = 0; j < i; j++){
                long value = acc[i] - acc[j];
                answer = Math.max(answer, Math.max(value, -value));
            }
        }
        
        return answer;
    }
}
