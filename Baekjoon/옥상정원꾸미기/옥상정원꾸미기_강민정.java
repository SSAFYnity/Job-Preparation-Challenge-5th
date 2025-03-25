import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 1 <= 빌딩의 개수 <= 80,000
        long answer = 0;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 현재 빌딩보다 낮거나 같은 높이의 빌딩들은 볼 수 없으므로 pop
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            // 현재 빌딩을 볼 수 있는 빌딩의 수는 스택에 쌓인 빌딩 수
            answer += stack.size();
            stack.push(i);  // 현재 빌딩의 인덱스를 스택에 푸시
        }

        System.out.println(answer);
    }
}
