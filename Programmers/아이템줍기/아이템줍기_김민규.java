import java.util.*;

public class Solution {
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[][] map;
    static int answer;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = Integer.MAX_VALUE;
        map = new int[101][101]; // 50인데 사이즈 2배 키울거라서 이렇게

        for (int i = 0; i < rectangle.length; i++) {
            // 사각형 꼭지점을 처음부터 2배로 키워서 올리기
            fill(2 * rectangle[i][0], 2 * rectangle[i][1], 2 * rectangle[i][2], 2 * rectangle[i][3]);
        }
        
        // 왜 2배? -> 1칸 짜리로 하면 접힐 때 감지를 못한다.
        bfs(2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY);

        return answer / 2; // 사이즈를 2배로 만들었으니까
    }

    public void fill(int r1, int c1, int r2, int c2) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (map[i][j] == 2) continue;
                    map[i][j] = 2;
                if (i == r1 || i == r2 || j == c1 || j == c2) { // 경계에 있다면
                    map[i][j] = 1; // 테두리에 1 넣기
                }
            }
        }
    }

    public void bfs(int si, int sj, int ti, int tj) {
        boolean[][] v = new boolean[101][101];
        Queue<Integer> q = new LinkedList<>();
        q.add(si);
        q.add(sj); // 두개씩 뽑을것임

        while (!q.isEmpty()) {
            int ci = q.poll();
            int cj = q.poll();

            for (int i = 0; i < 4; i++) {
                int ni = ci + di[i];
                int nj = cj + dj[i];
                if (!check(ni, nj)) continue;
                if (map[ni][nj] != 1 || v[ni][nj]) continue; // early return

                //map[nx][ny]==2 && 방문한적없음
                map[ni][nj] = map[ci][cj] + 1; // 갈 곳에 하나 더한다(길 크기) -> 첫 길이라면 2->3->4 이렇게 됨
                if (ni == ti && nj == tj) {
                    answer = Math.min(answer, map[ni][nj]);
                    continue;
                }
                v[ni][nj] = true;
                q.add(ni);
                q.add(nj);
            }
        }
    }

    public boolean check(int ci, int cj) {
        return ci >= 0 && cj >= 0 && ci <= 100 && cj <= 100;
    }
}