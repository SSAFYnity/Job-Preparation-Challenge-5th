package Baekjoon.옥상정원꾸미기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 옥상정원꾸미기_김범규 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int[] buildings = new int[N];
        for(int i = 0; i < N; i++){
            buildings[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < buildings.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= buildings[i]) {
                stack.pop();
            }
            stack.push(buildings[i]);
            result += stack.size() - 1; // 현재 건물을 제외한 스택에 남아 있는 건물 개수
        }

        System.out.println(result);

    }
}
