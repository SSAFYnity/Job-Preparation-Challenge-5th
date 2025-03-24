class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left=1;
        int right=200000000;
        int mid=0;

        while(left <= right){
            int zero=0;
            mid=(left+right)/2;
            for(int stone : stones){
                if(stone-mid <= 0){
                    zero+=1;
                }else{
                    zero=0;
                }
                if(zero >= k){
                    break;
                }
            }
            if(zero < k){
                left=mid+1;
            }else{
                right=mid-1;
                answer=mid;
            }
        }

        return answer;
    }
}