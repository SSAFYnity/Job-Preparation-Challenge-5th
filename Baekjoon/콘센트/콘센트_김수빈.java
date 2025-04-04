import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
        Integer[] times = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        Arrays.sort(times, Collections.reverseOrder());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            pq.offer(0);  // 초기 콘센트 0초 세팅
        }
        for (int time : times) {
            int now =pq.poll();  // 가장 빨리 끝나는 콘센트 꺼내기
            pq.offer(now + time);  // 거기에 현재 기기 추가해서 다시 넣기
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            answer = Math.max(answer, pq.poll());
        }
        
        System.out.println(answer);

    }
}
