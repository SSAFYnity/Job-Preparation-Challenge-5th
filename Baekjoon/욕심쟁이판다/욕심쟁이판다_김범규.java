package Baekjoon.욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이판다_김범규 {
    static int n;
    static int[][] dp;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        dp = new int[n][n];
        map = new int[n][n];

        //값 넣기
        for(int i = 0; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(dfs(i, j), max);
            }   
        }

        System.out.println(max);
    }
    
    public static int dfs(int r, int c){
        if(dp[r][c] != 0){
            return dp[r][c];
        }

        //방문 처리와 자기지역은 입장한 것이므로
        dp[r][c] = 1;

        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr >= 0 && nr < n && nc >= 0 && nc < n){
                //더 큰 곳으로 가야함
                if(map[nr][nc] > map[r][c]){
                    dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
                }
            }
        }
        return dp[r][c];
    }
}

