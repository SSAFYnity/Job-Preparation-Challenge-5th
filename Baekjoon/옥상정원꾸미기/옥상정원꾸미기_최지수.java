import java.util.*;
import java.io.*;

public class Main {
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        
        int n = read();
        long answer = 0L;
        // 아무것도 없으면 일단 스택에 넣어
        // 다음 넘어갔더니 나보다 작아 그럼 다시 스택에 넣고 지금 본 것도 스택에 넣어
        // 나(스택에서 꺼낸 거)보다 커 그럼 현재거의 인덱스 - 내 인덱스 - 1 한만큼 답 배열에
        // 그리고 다시 꺼내, 현재 거랑 비교해서 인덱스 크면 다시 차곡차곡 넣어

        Stack<Integer> stack = new Stack<>();
        int[] buildings = new int[n+1];
        for (int i = 0; i < n; i++) {
            buildings[i] = read();
            while (!stack.isEmpty()) {
                int me = stack.pop();
                if (buildings[me] > buildings[i]) {
                    stack.push(me);
                    break;
                } else {
                    answer += (i - me - 1);
                }
            }
            stack.push(i);
        }

        int i = n;
        buildings[i] = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            int me = stack.pop();
            if (buildings[me] > buildings[i]) {
                stack.push(me);
                break;
            } else {
                answer += (i - me - 1);
            }
        }

        System.out.println(answer);
    }
}

