import java.util.*;

/*
아이디어 : 슬라이딩윈동 +이분탐색
1. 사람 수 하나하나 탐색 -> 시간초과 -> 이진탐색으로 좁히기
2. 각 사람수마다 건널 수 있는지 -> k만큼 연속으로 몇 개의 돌이 0이하인지 -> 약한 슬라이딩 윈도우
*/

class Solution {
    public int solution(int[] stones, int k) {
        int start = 1;
        int end = 200000000; // 원소 값의 최댓값
        
        int answer = 0;
        
        while(start <= end){
            int mid = (start+end)/2;
            
            if(check(stones, k, mid)){  // 해당 인원이 전부 건널 수 있는지 확인
                answer = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        
        return answer;
    }
    
    boolean check(int[] stones, int k, int mid){
        int cnt  = 0; // 건넌 친구 수
        
        for(int i = 0; i < stones.length; i++){
            if(stones[i] - mid < 0){    // mid명이 건넌후 높이 -> 밟은 수 없는 상태
                cnt++;
                if(cnt >= k) return false;  // 연속으로 k개 이상 -> 밟을 수 없음
            }else cnt = 0;  // 밟을 수 있는 돌 나옴 -> 다시 초기화
        }

        return true;
    }
}