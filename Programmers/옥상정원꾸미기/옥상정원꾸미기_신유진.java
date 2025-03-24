import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 데이터 받기
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        // 벤치마킹이 가능한 빌딩의 수의 합 구하기
        long answer = 0;
        Stack<Integer> garden = new Stack<>();
        garden.add(data[0]);
        for (int i = 1; i < N; i++) {
            while (!garden.isEmpty()) {
                int value = garden.peek();
                if (value > data[i])
                    break;

                garden.pop();
            }
            answer += garden.size();
            garden.add(data[i]);
        }

        System.out.println(answer);
    }
}