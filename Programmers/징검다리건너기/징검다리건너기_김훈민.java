import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int start = 0;
        int end = stones[0];
        for(int s : stones){
            end = Math.max(end, s);
        }
        int mid = 0;
        while(start <= end){
            mid = (start + end) / 2;
            if(canGo(stones, k, mid)){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    boolean canGo(int[] stones,int k,int mid){
        int temp = 0;
        for(int s : stones){
            if (s - mid < 0) temp++;
            else temp = 0;

            if (temp >= k) return false;
        }
        return true;
    }
}