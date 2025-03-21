import java.util.*;

public class Solution {
    static boolean[] answer;
    static int[] limit;
    static boolean[][][] visit;

    public static void dfs(int a, int b, int c) {
        if (visit[a][b][c]) {
            return;
        }

        visit[a][b][c] = true;

        if (a == 0) {        // 첫 번째 물통이 비었을 경우
            answer[c] = true;
        }

        for (int i = 0; i < 3; i++) {       // 물을 주는 쪽
            for(int j=0; j<3; j++) {        // 물을 받는 쪽
                if(i == j) {        // 같은 물통이라면
                    continue;
                }
                int[] copied = new int[]{a, b, c};  // 깊은 복사
                if (copied[i] > 0 && copied[j] < limit[j]) {
                    int water = Math.min(limit[j] - copied[j], copied[i]);
                    copied[i] -= water;
                    copied[j] += water;
                    dfs(copied[0], copied[1], copied[2]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        limit = new int[3];
        limit[0] = sc.nextInt();
        limit[1] = sc.nextInt();
        limit[2] = sc.nextInt();
        answer = new boolean[201];
        visit = new boolean[201][201][201];

        dfs(0, 0, limit[2]);  // 초기 상태에서 dfs 시작

        // 가능한 결과 출력
        for (int i = 0; i <= 200; i++) {
            if (answer[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
