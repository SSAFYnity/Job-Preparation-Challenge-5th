package Baekjoon.색종이붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김범규_색종이붙이기 {
    static int[][] board = new int[10][10];
    static int[] confetti = {0, 5, 5, 5, 5, 5};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 10; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void dfs(int r, int c, int cnt){
        if(r >= 9 && c > 9){
            min = Math.min(min, cnt);
            return;
        }

        if(min <= cnt){
            return;
        }

        if(c > 9){
            dfs(r + 1, 0, cnt);
            return;
        }

        if(board[r][c] == 1){
            for(int i = 5; i >= 1; i--){
                //붙일수 있는 구간이면
                if(confetti[i] > 0 && checkArea(r, c, i)){
                    attach(r, c, i, 0);
                    confetti[i]--;
                    dfs(r, c + 1, cnt + 1);
                    attach(r, c, i, 1);
                    confetti[i]++;
                }
            }
        }
        else {
            dfs(r, c + 1, cnt);
        }
    }

    //붙이거나 떼기
    public static void attach(int r, int c, int size, int state){
        for(int i = r; i < r + size; i++){
            for(int j = c; j < c + size; j++){
                board[i][j] = state;
            }
        }
    }

    public static boolean checkArea(int r, int c, int size){
        if(r + size > 10 || c + size > 10){
            return false;
        }
        for(int i = r; i < r + size; i++){
            for(int j = c; j < c + size; j++){
                //범위안에 0이 있으면 탐색 종료
                if(board[i][j] != 1){
                    return false;
                }
            }
        }

        return true;
    }


}