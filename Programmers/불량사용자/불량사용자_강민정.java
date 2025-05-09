class Solution {
    static String[] user_id;
    static String[] banned_id;
    static int answer;
    static int n;       // 유저 아이디 갯수
    static int m;       // 불량 사용자 아이디 갯수
    static int[] arr;
    static int[] permu;
    static boolean isAble;

    public boolean check() {
        for(int i=0; i<m; i++) {
            String userId = user_id[permu[i]];     // 유저 아이디
            String banId = banned_id[i];    // 불량 사용자 아이디
            if(userId.length() != banId.length()) {
                return false;
            }
            int idx = 0;
            while(idx < banId.length()) {
                char user = userId.charAt(idx);
                char ban = banId.charAt(idx);
                if(ban != '*' && user != ban) {
                    return false;
                }
                idx++;
            }
        }
        return true;
    }

    public void permutation(int depth, boolean[] visit) {
        if(depth == m) {
            if(check()) {
                isAble = true;
            }
            return;
        }
        for(int i=0; i<m; i++) {
            if(visit[arr[i]]) {
                continue;
            }
            visit[arr[i]] = true;
            permu[depth] = arr[i];
            permutation(depth + 1, visit);
            visit[arr[i]] = false;
        }
    }

    public void dfs(int depth, int start) {
        if(depth == m) {
            permutation(0, new boolean[n]);
            if(isAble) {
                answer++;
            }
            isAble = false;
            return;
        }
        for(int i=start; i<n; i++) {
            arr[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        this.user_id = user_id;
        this.banned_id = banned_id;
        n = user_id.length;
        m = banned_id.length;
        arr = new int[m];
        permu = new int[m];

        dfs(0, 0);
        return answer;
    }
}