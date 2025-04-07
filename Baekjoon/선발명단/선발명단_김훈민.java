import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[][] abilities = new int[11][11];
    static boolean[] isUsed = new boolean[11];
    static int result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            StringTokenizer st;
            for(int i = 0; i < 11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++){
                    abilities[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            reset();
            dfs(0, 0);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    static void reset() {
        result = 0;
        Arrays.fill(isUsed, false);
    }

    static void dfs(int sum, int depth){
        if(depth == 11){
            result = Math.max(sum, result);
            return;
        }
        for(int i = 0; i < 11; i++){
            if(!isUsed[i] && abilities[depth][i] != 0){
                isUsed[i] = true;
                dfs(sum + abilities[depth][i], depth + 1);
                isUsed[i] = false;
            }
        }
    }
}
