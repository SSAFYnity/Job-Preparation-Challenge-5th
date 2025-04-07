import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int N;
    static Stack<Node> stack = new Stack<>();
    static int[] buildings;
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(br.readLine());
        }
        for(int i = N-1; i >= 0; i--) {
            if(!stack.isEmpty() && stack.peek().height == buildings[i]) {
                stack.push(new Node(buildings[i], stack.pop().count + 1));
                continue;
            }
            if(stack.isEmpty() || stack.peek().height > buildings[i]) {
                stack.push(new Node(buildings[i], 1));
            }
            else{
                long temp = 0;
                while(!stack.isEmpty() && stack.peek().height < buildings[i]){
                    temp += stack.peek().count;
                    stack.pop();
                }
                stack.push(new Node(buildings[i], temp + 1));
                result += temp;
            }
        }
        System.out.println(result);
    }

    static class Node{
        int height;
        long count;

        Node(int height, long count){
            this.height = height;
            this.count = count;
        }
    }
}
