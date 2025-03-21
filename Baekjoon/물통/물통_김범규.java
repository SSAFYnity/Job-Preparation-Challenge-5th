package Baekjoon.물통;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class 물통_김범규 {
    static int A, B, C;
    static int[][][] visited;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 각 물통의 용량
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new int[A + 1][B + 1][C + 1];

        //물통을 옮길 수 있는 경우의 수
        //A -> B , A -> C, B -> A, B -> C, C -> A, C -> B 총 6가지
        dfs(0, 0, C);
        List<Integer> result = new ArrayList<>(set);
        Collections.sort(result);

        for(int r : result){
            System.out.print(r + " ");
        }       
    }

    public static void dfs(int a, int b, int c){
        if(visited[a][b][c] == 1){
            return;
        }

        visited[a][b][c] = 1;
        if(a == 0){
            set.add(c);
        }

        //6가지 경우의 수 적용
        //1. a -> b
        if(a + b > B){
            dfs(a + b - B, B, c);
        }
        else {
            dfs(0, a + b, c);
        }

        //2. a -> c
        if (a + c > C){
            dfs(a + c - C, b, C);
        }
        else {
            dfs(0, b, a + c);
        }

        //3. b -> a
        if(b + a > A){
            dfs(A, b + a - A, c);
        }
        else {
            dfs(b + a, 0, c);
        }

        //4. b -> c
        if(b + c > C){
            dfs(a, b + c - C, C);
        }
        else {
            dfs(a, 0, b + c);
        }

        //5. c -> a
        if(c + a > A){
            dfs(A, b, c + a - A);
        }
        else {
            dfs(a + c, b, 0);
        }

        //6. c -> b
        if(c + b > B){
            dfs(a, B, c + b - B);
        }
        else {
            dfs(a, b + c, 0);
        }
    }
}