import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, K, L, dir;
    private static Deque<Pos> dq = new ArrayDeque<>();
    private static int[][] arr;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static Map<Integer, Character> map = new HashMap<>();

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            map.put(time, c);
        }
        arr[1][1] = 2;
        dq.offerFirst(new Pos(1, 1));
        int now = 0;
        dir = 0;
        while (true) {
            now++;
            Pos head = dq.getFirst();
            int nowX = head.x;
            int nowY = head.y;
            int nextX = nowX + dx[dir];
            int nextY = nowY + dy[dir];
            // 벽 또는 몸과 충돌
            if (!(1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N) || arr[nextX][nextY] == 2) {
                break;
            }
            // 사과가 없는 경우
            if (arr[nextX][nextY] != 1) {
                Pos tail = dq.pollLast();
                arr[tail.x][tail.y] = 0;
            }
            arr[nextX][nextY] = 2;
            dq.offerFirst(new Pos(nextX, nextY));
            // 회전
            if (map.containsKey(now)) {
                char c = map.get(now);
                dir = c == 'D' ? (dir + 1) % 4 : (dir + 3) % 4;
            }
        }
        System.out.println(now);
    }

}
