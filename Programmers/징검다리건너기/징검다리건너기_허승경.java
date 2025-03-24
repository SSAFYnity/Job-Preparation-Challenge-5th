import java.util.*;

/*
아이디어 : 구현 -> 효율성 걸릴수도
*/

class Solution {
    int answer = 0;
    
    public int solution(int[] stones, int k) {
        int answer = getFriends(stones, k);
        
        return answer;
    }
    
    int getFriends(int[] stones, int k){
        int answer = 0; // 건넌 친구 수
        
        while(true){
            boolean flag = false;
            
            for(int i = 0; i < stones.length; i++){
                if(stones[i] > 0) stones[i]--;
                else{
                    int cnt = 0;
                    for(int j = i+1; j < stones.length; j++){
                        if(stones[j] > 0){
                            stones[j]--;
                            break;
                        }else{
                            cnt++;
                        }
                    }
                    if(cnt > k){
                        flag = true;
                    }
                }
            }
            
            if(flag) break;
            else answer++;
        }
        
        return answer;
    }
}