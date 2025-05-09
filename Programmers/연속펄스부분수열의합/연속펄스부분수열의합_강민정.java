class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long num1 = 0;
        long num2 = 0;
        int num = 1;

        for(int i=0; i<n; i++) {
            num1 += sequence[i] * num;
            num2 += sequence[i] * (num * -1);

            num1 = Math.max(num1, 0);
            num2 = Math.max(num2, 0);

            num *= -1;
            answer = Math.max(answer, Math.max(num1, num2));
        }

        return answer;
    }
}