import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static Stack<Integer> stack = new Stack<>();
    private static int N;
    private static int[] arr;
    private static long ans = 0;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int now : arr) {
            while (!stack.isEmpty() && stack.peek() <= now) {
                stack.pop();
            }
            ans += stack.size();
            stack.push(now);
        }
        System.out.println(ans);
    }
}
