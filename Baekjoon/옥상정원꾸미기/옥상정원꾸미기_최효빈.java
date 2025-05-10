package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_6198 {

    private static class Node {
        int height;
        int count;

        public Node(int height, int count){
            this.height = height;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] buildings = new int[N];
        Stack<Node> st = new Stack<>();

        for(int i = 0; i < N; i++){
            int input = Integer.parseInt(br.readLine());
            buildings[i] = input;
        }

        long answer = 0;
        st.push(new Node(buildings[N-1], 0));

        for (int i = N-2; i >= 0; i--) {
            int currHeight = buildings[i];
            int count = 0;
            while(!st.isEmpty()){
                if(st.peek().height >= currHeight)
                    break;
                count += (1 + st.peek().count);
                st.pop();
            }
            answer += count;
            st.push(new Node(currHeight, count));
        }

        System.out.println(answer);
    }
}
