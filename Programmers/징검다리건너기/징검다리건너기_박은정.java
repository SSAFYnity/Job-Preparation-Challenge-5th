class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        // stones : 징검다리(디딤돌)
        int min = 1, max = 200_000_000;
        while(min <= max) {
            int mid = (min + max) / 2;
            if(canCross(stones, k, mid)) {
                min = mid + 1;
                answer = Math.max(answer, mid);
            }else {
                max = mid - 1;
            }
        }
        return answer;
    }

    // static boolean canCross(int[] stones, int maxJump, int pplCnt) {
    //     int skip = 0;
    //     for(int stone : stones){
    //         if(stone - pplCnt < 0) {
    //             skip += 1;
    //         }else {
    //             skip = 0;
    //         }
    //         if(skip == maxJump){
    //             return false;
    //         }
    //     }
    //     return true;
    // }
    
    static boolean canCross(int[] stones, int maxJump, int pplCnt){
        int[] newStones = new int[stones.length];
        for(int i = 0; i < stones.length; i++){
            newStones[i] = stones[i] - pplCnt;
        }
        int cnt = 0;
        for(int i = 0; i < newStones.length; i++){
            if(newStones[i] < 0) {
                cnt += 1;
                if(cnt >= maxJump) {
                    return false;
                }
            }else {
                cnt = 0;
            }
        }
        return true;
    }
}