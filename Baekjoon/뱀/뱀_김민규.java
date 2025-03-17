import java.io.*;
import java.util.*;

public class Main {
    static int n, k, l;
    static int[][] map;

    static class Dir {
        int t;
        char d;

        Dir(int t, char d) {
            this.t = t;
            this.d = d;
        }
    }

    static Queue<Dir> dir;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1]; // 1base
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        dir = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            // 사과
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1; // 사과 표시
        }

        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            // 방향변화
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            // t초 후 d 방향으로 90도 회전 -> L이면 반시계, D이면 시계
            dir.offer(new Dir(t, d));
        }

        System.out.println(go());

    }

    /**
     * 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
     * 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
     * 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
     * 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
     */
    static class Node {
        int i, j;
        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    public static int go() {
        Deque<Node> snake = new ArrayDeque<>();
        snake.offer(new Node(1, 1));
        int curDir = 1, time = 0;

        while (true) {
            time++;
            Node head = snake.peekLast();
            int ni = head.i + di[curDir];
            int nj = head.j + dj[curDir];

            if (!canGo(ni, nj) || isSnake(snake, ni, nj)) return time;

            snake.offerLast(new Node(ni, nj));
            if (map[ni][nj] == 1) {
                map[ni][nj] = 0;
            } else {
                snake.pollFirst(); // 사과를 못먹었으면 늘어난 꼬리를 잘라야됨
            }

            if (!dir.isEmpty() && dir.peek().t == time) {
                Dir tmp = dir.poll();
                curDir = (tmp.d == 'L') ? (curDir + 3) % 4 : (curDir + 1) % 4;
            }
        }
    }

    public static boolean canGo(int i, int j) {
        return i > 0 && i <= n && j > 0 && j <= n;
    }

    public static boolean isSnake(Deque<Node> snake, int ni, int nj) {
        // 갈 위치가 뱀의 몸인지
        for (Node node : snake) {
            if (node.i == ni && node.j == nj) return true;
        }
        return false;
    }
}