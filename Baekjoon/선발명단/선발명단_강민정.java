import java.io.*;
import java.util.*;

public class Solution {
    final static int MAX_SIZE = 11;

    static int answer;
    static boolean[] visit;
    static int[][] arr;

    public static void dfs(int depth, int sum) {
        if(depth == MAX_SIZE) {
            answer = Math.max(answer, sum);
            return;
        }
        for(int i=0; i<MAX_SIZE; i++) {
            if(visit[i] || arr[depth][i] == 0) {
                continue;
            }
            visit[i] = true;
            dfs(depth + 1, sum + arr[depth][i]);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int c = Integer.parseInt(br.readLine());    // 테스트케이스 갯수
        for(int tc=0; tc<c; tc++) {
            arr = new int[MAX_SIZE][MAX_SIZE];
            answer = 0;
            visit = new boolean[MAX_SIZE];
            for(int i=0; i<MAX_SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<MAX_SIZE; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            System.out.println(answer);
        }
    }
}