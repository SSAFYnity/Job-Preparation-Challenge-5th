import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph = new int[10][10];
    static int result = Integer.MAX_VALUE;
    static int[] papers = new int[5];

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st;
        Arrays.fill(papers, 5);
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    static void dfs(int start_x, int start_y, int depth) {
        if (depth >= result)
            return;
        if(start_y >= 10) {
            start_y = 0;
            start_x++;
        }
        if(start_y == 0 && start_x >= 10) {
            result = Math.min(result, depth);
            return;
        }
        if (graph[start_x][start_y] == 1) {
            for (int w = 4; w >= 0; w--) {
                if (canAttach(start_x, start_y, w + 1)) {
                    attach(start_x, start_y, w + 1);
                    dfs(start_x, start_y + w + 1, depth + 1);
                    dettach(start_x, start_y, w + 1);
                }
            }
        }
        else {
            dfs(start_x, start_y + 1, depth);
        }

    }


    static void attach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                graph[i][j] = 0;
            }
        }
        papers[size - 1]--;
    }

    static void dettach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                graph[i][j] = 1;
            }
        }
        papers[size - 1]++;
    }

    static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10 || papers[size - 1] == 0) {
            return false;
        }
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (graph[i][j] != 1)
                    return false;
            }
        }
        return true;
    }
}