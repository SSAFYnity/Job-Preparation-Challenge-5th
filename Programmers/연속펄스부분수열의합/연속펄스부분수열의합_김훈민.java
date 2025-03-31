class Solution {
    long[] oddArr;
    long[] evenArr;
    long result = Long.MIN_VALUE;
    int len;
    public long solution(int[] sequence) {
        len = sequence.length;
        oddArr = new long[sequence.length];
        evenArr = new long[sequence.length];
        calcArrValue(sequence);
        findMaxValue(oddArr);
        findMaxValue(evenArr);
        return result;
    }

    void calcArrValue(int[] seq){
        boolean even = true;
        oddArr[0] = seq[0] * -1;
        evenArr[0] = seq[0];
        for(int i = 1; i < seq.length; i++){
            if(even){
                evenArr[i] += (evenArr[i-1] + seq[i] * -1);
                oddArr[i] += (oddArr[i-1] + seq[i]);
            } else {
                evenArr[i] += (evenArr[i-1] + seq[i]);
                oddArr[i] += (oddArr[i-1] + seq[i] * -1);
            }
            even = !even;
        }
    }

    void findMaxValue(long[] arr){
        long minValue = 0;
        long maxSubValue = Long.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            maxSubValue = Math.max(maxSubValue, arr[i] - minValue);
            minValue = Math.min(minValue, arr[i]);
        }
        result = Math.max(result, maxSubValue);
    }
}