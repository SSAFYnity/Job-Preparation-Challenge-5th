import java.util.*;

class Solution {
    static int N;
    static int maxDepth;
    static int result;
    static String[] userIds;
    static boolean[] isUsed;
    static String[] bannedIds;
    static Set<String> check = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        maxDepth = banned_id.length;
        userIds = user_id;
        bannedIds = banned_id;
        isUsed = new boolean[N];
        dfs(0);
        return result;
    }

    void dfs(int depth){
        if(depth == maxDepth){
            String key = makeKey();
            if(check.contains(key))
                return;
            check.add(key);
            result++;
            return;
        }
        for(int i = 0; i < N; i++){
            if(!isUsed[i] && canUsed(i, depth)){
                isUsed[i] = true;
                dfs(depth + 1);
                isUsed[i] = false;
            }
        }
    }

    boolean canUsed(int i, int depth){
        if(userIds[i].length() != bannedIds[depth].length())
            return false;
        for(int j = 0; j < userIds[i].length(); j++){
            if((userIds[i].charAt(j) != bannedIds[depth].charAt(j)) && (bannedIds[depth].charAt(j) != '*'))
                return false;
        }
        return true;
    }

    String makeKey(){
        String temp = "";
        for(boolean b : isUsed){
            if(b)
                temp += temp + "1";
            else
                temp += temp + "0";
        }
        return temp;
    }
}