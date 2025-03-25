class Solution {
    public int solution(int[] stones, int k) {
        int top = 200_000_000;
        int bottom = 0;
        
        while(bottom < top){
            int mid = (top + bottom) / 2;
            
            
            int skipCnt = 0;
            for(int idx = 0; idx < stones.length; idx++){
                if(stones[idx] - mid < 0){
                    if(++skipCnt >= k)
                        break;
                }else{
                    skipCnt = 0;
                }
            }
            
            if(skipCnt < k){
                bottom = mid + 1;
            }else{
                top = mid;
            }
        }
        return bottom - 1;
    }
}
