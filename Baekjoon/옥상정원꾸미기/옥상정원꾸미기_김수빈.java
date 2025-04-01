import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 빌딩 수
        int[] buildings = new int[N];  // 빌딩 높이
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }

        Stack<Long> stack = new Stack<>();
        long cnt = 0;

        for (long building : buildings) {
            if (stack.isEmpty()) {
                stack.push(building);
            } else {
                // 만약 꼭대기보다 작다면
                if (stack.peek() > building) {
                    stack.push(building);
                } else {
                    // 스택이 비기 전까지
                    while (!stack.isEmpty()) {
                        // 만약 빌딩이 마지막보다 작다면
                        if (stack.peek() > building) {
                            break;
                        }
                        // 그 전까지 pop
                        stack.pop();
                    }
                    stack.push(building);
                }
                cnt += stack.size() - 1;
            }
        }

    System.out.println(cnt);
    }
}
