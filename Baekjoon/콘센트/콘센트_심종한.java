import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;
    private static Queue<Integer> power = new PriorityQueue<>();
    private static Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2) * -1);

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }
        System.out.println(solve());
    }

    private static int solve() {
        for (int i = 0; i < M && !pq.isEmpty(); i++) {
            power.offer(pq.poll());
        }
        while (!pq.isEmpty()) {
            int firstTime = power.peek();
            while (!power.isEmpty() && power.peek() == firstTime) {
                power.poll();
            }
            while (!pq.isEmpty() && power.size() < M) {
                power.offer(firstTime + pq.poll());
            }
        }
        int ans = 0;
        while (!power.isEmpty()) {
            ans = Math.max(ans, power.poll());
        }
        return ans;
    }
}
