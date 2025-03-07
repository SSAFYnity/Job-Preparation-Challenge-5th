import java.util.Scanner;

public class Main {
    static int N = 10; // 보드 크기 10x10
    static int[][] board = new int[N][N];
    static int[] paperCount = new int[6]; // 색종이의 개수 (1x1, 2x2, ..., 5x5)
    static int result = Integer.MAX_VALUE; // 최소 색종이 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 보드 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 백트래킹 시작
        dfs(0, 0, 0);

        // 결과 출력
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // (x, y)부터 시작하여 덮을 수 있는지 확인
    public static void dfs(int x, int y, int count) {
        // 덮을 곳을 찾았으면 결과 체크
        if (x == N) {
            result = Math.min(result, count);
            return;
        }

        if (y == N) {
            dfs(x + 1, 0, count);
            return;
        }

        if (board[x][y] == 0) {
            dfs(x, y + 1, count); // 이 칸은 이미 덮여 있음
            return;
        }

        // 색종이 크기를 5x5에서 1x1까지 시도
        for (int size = 5; size >= 1; size--) {
            if (paperCount[size] < 5 && canPlace(x, y, size)) {
                placePaper(x, y, size, 0); // 색종이 배치
                paperCount[size]++; // 색종이 개수 증가
                dfs(x, y + 1, count + 1); // 다음 칸으로
                paperCount[size]--; // 색종이 개수 감소
                placePaper(x, y, size, 1); // 색종이 제거
            }
        }
    }

    // 색종이 크기만큼 덮을 수 있는지 확인
    public static boolean canPlace(int x, int y, int size) {
        if (x + size > N || y + size > N) return false; // 보드 밖으로 나가면 안 됨
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (board[i][j] == 0) return false; // 덮을 수 없는 칸이면 안 됨
            }
        }
        return true;
    }

    // 색종이 크기만큼 덮기 또는 제거하기
    public static void placePaper(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = value; // 1이면 덮고 0이면 제거
            }
        }
    }
}
