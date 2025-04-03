import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int [] arr1 = new int[sequence.length]; // 1로 시작
        int [] arr2 = new int[sequence.length]; // -1로 시작
        
        int sign = 0;
        for(int i = 0; i < sequence.length; i++){
            if(i%2 == 0)sign = 1;
            else sign = -1;
            
            arr1[i] = sequence[i]*sign;
            arr2[i] = sequence[i]*-sign;
        }

        return Math.max(getMaxSequence(arr1), getMaxSequence(arr2));
    }
    
    long getMaxSequence(int [] arr){
        long maxSum = arr[0];   // 전체 수열 좀 전체값
        long curSum = arr[0];   // 현재 수열의 값
        
        for(int i = 1; i < arr.length; i++){
            curSum = Math.max(arr[i], curSum+arr[i]);   // 현재 단독, 이전값+현재값
            maxSum = Math.max(maxSum, curSum);
        }
        
        return maxSum;
    }
}