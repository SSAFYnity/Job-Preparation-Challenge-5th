import java.util.*;

class Solution {
    
    String[] userId;
    String[] bannedId;
    boolean[][] visited;
    Set<Integer> set;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.userId = user_id;
        this.bannedId = banned_id;
        set = new HashSet<>();
        visited = new boolean[bannedId.length + 1][(1 << userId.length) + 1];
        dfs(0, 0);
        return set.size();
    }
    
    private void dfs(int depth, int bit) {
        if (depth == bannedId.length) {
            set.add(bit);
            return;
        }
        if (visited[depth][bit]) {
            return;
        }
        visited[depth][bit] = true;
        for (int i = 0; i < userId.length; i++) {
            if ((bit & (1 << i)) != 0) {
                continue;
            }
            if (check(userId[i], bannedId[depth])) {
                dfs(depth + 1, bit | (1 << i));
            }
        }
    }
    
    private boolean check(String target, String filter) {
        if (target.length() != filter.length()) {
            return false; 
        }
        for (int i = 0; i < target.length(); i++) {
            if (filter.charAt(i) != '*' && target.charAt(i) != filter.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
