import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int[][] map = new int[10][10];
    static int[] useCount = new int[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        DFS(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void DFS(int x, int y, int cnt) {
        if (cnt >= answer) return;
        if (y == 10) {
            answer = cnt;
            return;
        }
        if (x == 10) {
            DFS(0, y + 1, cnt);
            return;
        }
        if (map[y][x] == 0) {
            DFS(x + 1, y, cnt);
            return;
        }
        
        for (int size = 5; size > 0; size--) {
            if (useCount[size - 1] < 5 && canPatch(x, y, size)) {
                applyPatch(x, y, size, 0);
                useCount[size - 1]++;
                DFS(x + 1, y, cnt + 1);
                useCount[size - 1]--;
                applyPatch(x, y, size, 1);
            }
        }
    }

    static boolean canPatch(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[y + i][x + j] == 0) return false;
            }
        }
        return true;
    }

    static void applyPatch(int x, int y, int size, int value) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[y + i][x + j] = value;
            }
        }
    }
}
