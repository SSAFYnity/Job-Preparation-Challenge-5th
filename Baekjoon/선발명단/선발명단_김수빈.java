import java.util.*;
import java.io.*;

public class Main {
    static int maxVal = 0;
    private static void pickBestMember(int[][] caps, int idx, int sum, boolean[] visited) {
        if (idx == 11) {
            maxVal = Math.max(maxVal, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            // 능력치가 0이 아니고, 해당 선수가 안뽑혔을 때
            if (caps[i][idx] != 0 && !visited[i]) {
                visited[i] = true;
                pickBestMember(caps, idx+1, sum + caps[i][idx], visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        for (int t = 0; t < TC; t++) {
            maxVal = 0;
            int[][] capabilities = new int[11][11];
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    capabilities[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[] visited = new boolean[11];
            pickBestMember(capabilities, 0, 0, visited);
            System.out.println(maxVal);
        }
    }
}
