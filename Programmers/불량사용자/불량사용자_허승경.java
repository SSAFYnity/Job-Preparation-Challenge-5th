import java.util.*;

/*
아이디어: 일단 구현
1. b_id에 해당하는 u_id를 list에 저장하기 -> List<List<String>> -> 어차피 순서대로 하니까 map 안씀
2. 위의 list 가지고 순열조합 만드고, set에 넣기
3. set.size()가 정답
*/

class Solution {
    List<List<String>> list = new ArrayList<>();
    Set<Set<String>> set = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        for(int i = 0; i < banned_id.length; i++){
            list.add(new ArrayList<>());
        }
        
        // 1. b_id에 해당하는 u_id의 리스트 
        for(int idx = 0; idx < banned_id.length; idx++){
            String target = banned_id[idx];
            
            for(String str : user_id){
                if(str.length() == target.length()){
                    boolean flag = true;
                    
                    for(int j = 0; j < target.length(); j++){
                        if(target.charAt(j) == '*') continue;
                        if(target.charAt(j) != str.charAt(j)){
                            flag = false;
                            break;
                         }
                    }
                    
                    if(flag){
                        list.get(idx).add(str);
                    }
                }
            }
        }
        
        // 2. 순열 조합 구하기
        backtracking(0, new HashSet<>());
        
        // 3. 정답 출력
        return set.size();
        
    }
    
    void backtracking(int depth, Set<String> curSet){
        // 가지치기
        if(depth == list.size()){        // 하나 조합 만들어진 경우
            set.add(new HashSet<>(curSet));
            return;   
        }
        
        
        for(String tmp : list.get(depth)){
            if(curSet.contains(tmp)) continue;
            
            curSet.add(tmp);
            backtracking(depth+1, curSet);
            curSet.remove(tmp);
        }
    }
}