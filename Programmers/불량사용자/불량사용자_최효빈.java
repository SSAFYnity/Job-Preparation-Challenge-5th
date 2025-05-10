import java.util.*;

class Solution {
    Map<Integer, Boolean> map = new HashMap<>();
    int answer = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        comb(0, 0, user_id, banned_id);
        return answer;
    }
    
    
    public void comb(int cnt, int visited, String[] user_id, String[] banned_id){
        if(cnt == banned_id.length){
            if(map.get(visited) == null){
                answer++;
                map.put(visited, true);
            }
            return;
        }

        String pattern = banned_id[cnt];

        for(int j = 0; j < user_id.length; j++){    
            if((visited & (1<<j)) > 0)
                continue;
            String user = user_id[j];
            boolean result = compareId(user, pattern);

            if(result)
                comb(cnt+1, visited | (1<<j), user_id, banned_id);
        }
    }
    
    
    public boolean compareId(String user, String pattern){
        if(user.length() != pattern.length())
            return false;
    
        for(int j = 0; j < user.length(); j++){
            char letterU = user.charAt(j);
            char letterP = pattern.charAt(j);

            if(letterP == '*')
                continue;

            if(letterU != letterP)
                return false;
        }
        
        return true;
    }
}

