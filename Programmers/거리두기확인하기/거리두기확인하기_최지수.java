import java.util.*;

class Solution {
    
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
    
    public boolean isOut(int y, int x) {
        return y < 0 || y >= 5 || x < 0 || x >= 5;
    }
    
    public boolean find(int j, int k, String[] place) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {j, k, 0});
        boolean[][] visit = new boolean[5][5];
        visit[j][k] = true;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            if (now[2] != 0 && now[2] <= 2 && place[now[0]].charAt(now[1]) == 'P') {
                return true;
            }
            if (now[2] >= 2) continue;
            for (int l = 0; l < 4; l++) {
                int y = now[0] + dr[0][l];
                int x = now[1] + dr[1][l];
                if (isOut(y,x) || visit[y][x] || place[y].charAt(x) == 'X') continue;
                que.add(new int[] {y,x,now[2]+1});
                visit[y][x] = true;
            }
        }
        return false;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[] {1,1,1,1,1};
        
        for (int i = 0; i < 5; i++) {
            // System.out.println("\n대기실 " + (i+1));
            String[] place = places[i];
            int cnt = 4;
            xx: for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (place[j].charAt(k) == 'P') {
                        if (find(j, k, place)) {
                            answer[i] = 0;
                            break xx;
                        }
                        if (--cnt == 0) break xx;
                    }
                }
            }
        }
        return answer;
    }
}