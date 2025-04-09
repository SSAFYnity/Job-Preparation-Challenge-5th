import java.util.*;

class Solution {
    int n, m;
    char[][] sto;
    int[] count = new int[26];
    int[][] dr = {{0,0,-1,1}, {1,-1,0,0}};
    boolean isOut(int y, int x) { return y < 0 || y >= n || x < 0 || x >= m; }
    boolean isLive(int y, int x) {return sto[y][x] != 0 && count[sto[y][x]-'A'] != 0;}
    class WV {
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    int removeEdge(char target) {
        int cnt = 0;
        Queue<WV> que = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        for (int i = 1; i < m-1; i++) {
            que.add(new WV(0, i));
            que.add(new WV(n-1, i));
            visit[0][i] = true;
            visit[n-1][i] = true;
        }
        for (int i = 0; i < n; i++) {
            que.add(new WV(i, 0));
            que.add(new WV(i, m-1));
            visit[i][0] = true;
            visit[i][m-1] = true;
        }
        while (!que.isEmpty() && count[target-'A'] != 0) {
            // 지금 방문한 곳이 통과할 수 있는 곳이다 -> 투어 돌아야함
            // 지금 방문한 곳이 살아있는 
            // // 타깃이 아니다 -> 걍 컨티뉴
            // // 타깃이다 -> 지우고 컨티뉴
            WV now = que.poll();
            char a = sto[now.y][now.x];            
            if (isLive(now.y, now.x)) {
                if (a == target) {
                    sto[now.y][now.x] = 0;
                    count[target-'A']--;
                    cnt++;
                }
                continue;
            }
            
            for (int d = 0; d < 4; d++) {
                int y = now.y + dr[0][d];
                int x = now.x + dr[1][d];
                if (isOut(y,x) || visit[y][x]) continue;
                que.add(new WV(y,x));
                visit[y][x] = true;
            }
        }
        return cnt;
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        
        sto = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sto[i][j] = storage[i].charAt(j);
                count[sto[i][j]-'A']++;
            }
        }

        for (String inst: requests) {
            char alpha = inst.charAt(0);
            if (count[alpha-'A'] == 0) continue;
            if (inst.length() == 1) {
                answer += removeEdge(inst.charAt(0));
            } else {
                answer += count[alpha-'A'];
                count[alpha-'A'] = 0;
            }
        }
        
        return n*m - answer;
    }
}