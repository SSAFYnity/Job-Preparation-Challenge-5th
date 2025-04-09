import java.util.*;
import java.io.*;

public class Main {

    public static int n, answer, sp, ep;
    public static int[][] map;
    public static boolean[][][] visited;
    public static ArrayList<int[]> start = new ArrayList<>();
    public static ArrayList<int[]> end = new ArrayList<>();
    public static int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0}}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n][2]; // [x][y][0/1] 0은 가로, 1은 세로

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                char c = str.charAt(j);

                if(c == 'B'){
                    start.add(new int[] {i,j});
                }else if(c == 'E'){
                    end.add(new int[] {i,j});
                }
            }
        }

        pos_check();
        bfs();
        System.out.println(answer);
    }

    // 시작,목표 통나무 배치. i가 차이나면 세로(1), 아니면 가로
    public static void pos_check(){
        // 시작 위치가 가로면 sp = 0, 세로면 sp = 1
        int num = start.get(0)[0] - start.get(1)[0];
        sp = (num == 0) ? 0 : 1;

        // 목표 위치가 가로면 ep = 0, 세로면 ep = 1
        num = end.get(0)[0] - end.get(1)[0];
        ep = (num == 0) ? 0 : 1;
    }

    // 맵 범위 벗어난거 패스
    public static boolean range_check(int i, int j, int pos){
        if (pos == 1) { // 세로일 때
            return (i <= 0 || i >= n - 1 || j < 0 || j >= n);
        } else { // 가로일 때
            return (i < 0 || i >= n || j <= 0 || j >= n - 1);
        }
    }

    // 방향을 전환할 수 있는지 체크
    public static boolean turn(int a, int b, int pos){
        // 나무 돌리기 가능한가
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0){
                    continue;
                }

                int na = a + i;
                int nb = b + j;

                // 맵 범위 벗어난 곳은 체크할 필요 없음
                if(na < 0 || na >= n || nb < 0 || nb >= n || map[na][nb] == 1){
                    return false;
                }
            }
        }

        // 돌렸을 때, 나무가 있거나 맵 벗어나진 않는지 체크
        if (range_check(a, b, pos) || tree_check(a, b, pos)){
            return false;
        }

        return true;
    }

    // 이동 시 나무 체크
    public static boolean tree_check(int a, int b, int pos){
        if (pos == 1) { // 세로
            for (int i = -1; i <= 1; i++) {
                if (map[a + i][b] == 1) {
                    return true;
                }
            }
        } else { // 가로
            for (int i = -1; i <= 1; i++) {
                if (map[a][b + i] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void bfs(){
        Queue<int[]> q = new LinkedList<>();

        // 초기 나무 중앙 위치, 방향, 움직인 횟수
        q.offer(new int[] {start.get(1)[0], start.get(1)[1], sp, 0});
        visited[start.get(1)[0]][start.get(1)[1]][sp] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0];
            int j = cur[1];
            int pos = cur[2];
            int cnt = cur[3];

            // 목표지점에 도달한 경우
            if (i == end.get(1)[0] && j == end.get(1)[1] && pos == ep) {
                answer = cnt;
                return;
            }

            // 나무 움직이기
            for (int idx = 0; idx < 4; idx++) {
                int ni = i + dirXY[idx][0];
                int nj = j + dirXY[idx][1];

                if (range_check(ni, nj, pos) || visited[ni][nj][pos] || tree_check(ni, nj, pos)) {
                    continue;
                }

                visited[ni][nj][pos] = true;
                q.offer(new int[] {ni, nj, pos, cnt + 1});
            }

            // 나무 돌리기
            if (turn(i, j, pos)) {
                int newPos = (pos == 1) ? 0 : 1;
                q.offer(new int[] {i, j, newPos, cnt + 1});
            }
        }
    }
}
