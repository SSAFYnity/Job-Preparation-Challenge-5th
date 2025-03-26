import java.io.*;
import java.util.*;
class Solution {
    static Set<Set<String>> result = new HashSet<>();
    static String[] userIds, bannedIds;
    public int solution(String[] user_id, String[] banned_id) {
        bannedIds = banned_id;
        userIds = user_id;
        back(0, new HashSet<>());
        return result.size();
    }
    static void back(int depth, Set<String> banned) {
        if(depth == bannedIds.length) {
            result.add(new HashSet<>(banned)); // 여기서 복사해서 넣어야 함
            return;
        }
        for(String userId : userIds) {
            if(banned.contains(userId)){
                continue;
            }
            if(checkIsBanned(userId, bannedIds[depth])) {
                banned.add(userId);
                back(depth + 1, banned);
                banned.remove(userId);
            }
        }
        
    }
    static boolean checkIsBanned(String userId, String bannedId) {
        if(userId.length()!= bannedId.length()) {
            return false;
        }
        for(int i = 0; i < userId.length(); i++){
            if(bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)){
                return false;
            }
        }
        return true;
        
    }
}