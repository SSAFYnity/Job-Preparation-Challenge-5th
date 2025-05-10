import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int[] arr = new int[3];
    private static boolean[][][] visited;
    private static Set<Integer> set = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[arr[0] + 1][arr[1] + 1][arr[2] + 1];
        dfs(0, 0, arr[2]);
        for (int ans : set) {
            sb.append(ans).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int a, int b, int c) {
        if (visited[a][b][c]) {
            return;
        }
        visited[a][b][c] = true;
        if (a == 0) {
            set.add(c);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    continue;
                }
                // i -> j로 전달
                int[] state = pour(new int[] {a, b, c}, i, j);
                dfs(state[0], state[1], state[2]);
            }
        }
    }

    private static int[] pour(int[] state, int from, int to) {
        int possibleAmount = Math.min(state[from], arr[to] - state[to]);
        state[from] -= possibleAmount;
        state[to] += possibleAmount;
        return state;
    }
}
