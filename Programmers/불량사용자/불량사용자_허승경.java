import java.util.*;

/*
아이디어: 일단 구현
1. user_id 배열을 <길이, 단어들> hashmap에 저장
2. 제재 아이디 목록 돌면서 해당 아이디 길이의 value 찾기
3. value에서 조건 맞는 단어 있으면 인덱스에 해당하는 배열 카운트 1, set 저장
4. 이미 set에 있으면 넘어가~~
5. 배열값 곱해버려~~
*/

class Solution {
    HashMap<Integer, ArrayList<String>> hmap = new HashMap<>();
    public int solution(String[] user_id, String[] banned_id) {
        Set<String> set = new HashSet<>();
        
        for(String id : user_id){
            int len = id.length();
            if(!hmap.containsKey(len)){
                ArrayList<String> list = new ArrayList<>();
                list.add(id);
                hmap.put(len, list);
            }else{
                hmap.get(len).add(id);
            }
        }
        
        int [] countArr = new int[banned_id.length];
        for(int i = 0; i < banned_id.length; i++){
            String target = banned_id[i];
            
            if(hmap.containsKey(target.length())){
                ArrayList<String> list = hmap.get(target.length());
                for(int j = 0; j < list.size(); j++){
                    boolean flag = true;
                    for(int k = 0; k < target.length(); k++){
                        if(target.charAt(k) == '*') continue;
                        else{
                            if(target.charAt(k) != list.get(j).charAt(k)){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(flag){
                        if(!set.contains(list.get(j))){
                            set.add(list.get(j));
                            countArr[i]++;
                        }
                    }
                }
            }
        }
        
        int answer = 1;
        for(int num : countArr){
            if(num == 0) continue;
            else answer*=num;
        }
        return answer;
    }
}