import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 뱀의 머리, 꼬리를 관리하기 위해서는 Deque가 필요하다.
 * 뱀의 머리가 늘어나면 맨앞에 추가하고, 꼬리가 줄어들면 맨뒤에 값을 제거해야기 때문이다.
 * 입력에서 사과의 위치를 입력받을 때 -1을 해줘야 한다. 아니면, 배열을 생성할 때 (n+1) * (n+1)로 생성해도 된다.
 */

public class Main {

    public static class Cmd {
        int time; // 게임 시작 시간으로부터 x초
        String direction; // 방향

        public Cmd(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    public static class Snake {
        int x;
        int y;

        public Snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 0; // 게임 시간
        n = Integer.parseInt(br.readLine()); // 2 <= 보드의 크기 <= 100
        arr = new int[n][n];
        int k = Integer.parseInt(br.readLine()); // 0 <= 사과의 개수 <= 100
        // 사과의 위치를 입력 받음
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            arr[x][y] = 2; // 사과가 있는 곳
        }
        // 뱀의 방향 변환 정보를 입력받기
        List<Cmd> cmdList = new ArrayList<Cmd>();
        int l = Integer.parseInt(br.readLine()); // 1 <= 뱀의 방향 변환 횟수 <= 100
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken(); // 왼쪽 L, 오른쪽 D

            cmdList.add(new Cmd(x, c));
        }

        Deque<Snake> snake = new ArrayDeque<Snake>();
        snake.add(new Snake(0, 0)); // 초기에 뱀이 있는 위치
        int cmdIdx = 0;
        // 우 하 좌 상
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };
        int d = 0;

        while (true) {
            Snake head = snake.peekFirst(); // 뱀의 머리

            if (cmdIdx < cmdList.size() && cmdList.get(cmdIdx).time == answer) {
                if (cmdList.get(cmdIdx++).direction.equals("L")) {
                    d -= 1;
                } else {
                    d += 1; // 오른쪽 회전
                }
                if (d == -1)
                    d = 3;
                else if (d == 4)
                    d = 0;
            }

            int nx = head.x + dx[d];
            int ny = head.y + dy[d];

            if (!isValid(nx, ny)) { // 범위를 벗어났거나 자신의 몸에 부딪힘
                answer++;
                break;
            }

            if (arr[nx][ny] != 2) { // 사과가 없는 칸
                // 꼬리가 위치한 칸을 비운다.
                if (!snake.isEmpty()) {
                    Snake tail = snake.pollLast(); // 마지막에 있는 값(꼬리) 제거
                    arr[tail.x][tail.y] = 0; // 꼬리 제거
                }
            }

            arr[nx][ny] = 1; // 뱀의 머리
            snake.addFirst(new Snake(nx, ny)); // 뱀의 머리가 늘어남
            answer++; // 시간 증가
        }

        System.out.println(answer);
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && arr[x][y] != 1;
    }

}