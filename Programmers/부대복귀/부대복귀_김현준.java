import java.util.*;

class Solution {
    static List<Integer>[] list;
    static boolean[] v;
    static int ans;
    static class Point {
        int w, cnt;
        Point(int w, int cnt) {
            this.w = w;
            this.cnt = cnt;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        list = new ArrayList[n+1];
        for(int i=1;i<=n;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<roads.length;i++) {
            list[roads[i][0]].add(roads[i][1]);
            list[roads[i][1]].add(roads[i][0]);
        }

        for(int i=0;i<sources.length;i++) {
            v = new boolean[n+1];
            ans = Integer.MAX_VALUE;
            int now = sources[i];
            v[now] = true;

            if(now == destination) answer[i] = 0;
            else {
                findRoute(destination, now, 0);

                if(ans == Integer.MAX_VALUE) answer[i] = -1;
                else answer[i] = ans;
            }
        }
        return answer;
    }

    public static void findRoute(int dest, int now, int move) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(now, move));
        boolean flag = false;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int c : list[p.w]) {
                // 방문하지 않은 길은 체크하고 Q에 추가
                // 가는 곳이 목적지라면? 바로 종료
                if(!v[c]) {
                    if(c == dest) {
                        flag = true;
                        break;
                    }
                    else {
                        v[c] = true;
                        Q.offer(new Point(c, p.cnt + 1));
                    }
                }
            }
            if(flag) {
                ans = p.cnt + 1;
                break;
            }
        }
    }
}