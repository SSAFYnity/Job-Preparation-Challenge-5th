import java.util.*;
import java.io.*;

public class 뱀_김수빈 {
    static int N;
    static int K;
    static int[][] ds = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // 방향
    static boolean flag = true;
    private static int[] movingSnake(int cr, int cc, Queue<int[]> snakePoses, int d, int X, int[][] board) {
        int count = 0;
        board[cr][cc] = -1;
        for (int i = 0; i < X; i++) {
            int nr = cr + ds[d][0];
            int nc = cc + ds[d][1];
            // 이동 가능 (벽 아니고, 몸통도 아니면)
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] != -1) {
                // 사과면
                if (board[nr][nc] == 1) {
                    snakePoses.offer(new int[] {nr, nc});  // 이동
                    board[nr][nc] = -1;  // 뱀 머리 위치
                } else {
                    int[] tailPos = snakePoses.poll();  // 꼬리 없애기
                    board[tailPos[0]][tailPos[1]] = 0;  // 보드 위치에서 꼬리 없애기
                    snakePoses.offer(new int[] {nr, nc});  // 이동
                    board[nr][nc] = -1;  // 뱀 머리 위치
                }
                count++;
                cr = nr;
                cc = nc;
            } else {
                flag = false;
                return new int[] {count, cr, cc};
            }
        }
        return new int[] {count, cr, cc};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 보드 크기
        K = Integer.parseInt(br.readLine());  // 사과 개수
        int[][] board = new int[N][N];
        Queue<int[]> snakePoses = new LinkedList<>();  // 뱀 위치
        int cr = 0, cc = 0;
        snakePoses.add(new int[] {cr, cc});
        int angle = 0;  // 각도
        for (int i = 0; i < K; i++) {
            String[] applePos = br.readLine().split(" ");
            board[Integer.parseInt(applePos[0]) - 1][Integer.parseInt(applePos[1]) - 1] = 1;  // 사과 위치에 1 표시
        }
        int time = 0;  // 걸린 시간
        int L = Integer.parseInt(br.readLine());  // 뱀 방향 변환 횟수
        for (int i = 0; i < L; i++) {
            String[] movPos = br.readLine().split(" ");
            int X = Integer.parseInt(movPos[0]);
            char C = movPos[1].charAt(0);
            int[] result = movingSnake(cr, cc, snakePoses, angle / 90, X, board);
            time += result[0];
            cr = result[1];
            cc = result[2];
            if (!flag) {
                System.out.println(time);
                return;
            }
            // 각도 바꾸기
            if (C == 'D') {
                angle = (angle + 90) % 360;
            } else {
                angle = (angle + 270) % 360;
            }

        }
    }
}
