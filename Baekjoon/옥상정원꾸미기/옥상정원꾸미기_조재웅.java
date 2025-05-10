import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long count = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int currentHeight = Integer.parseInt(br.readLine());
            while (!minHeap.isEmpty() && minHeap.peek() <= currentHeight) {
                minHeap.poll();
            }
            count += minHeap.size();
            minHeap.offer(currentHeight);
        }

        System.out.println(count);
    }
}
