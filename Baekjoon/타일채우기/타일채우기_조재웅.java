import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 != 0)
        {
            System.out.println(0); // if N is odd it can't be filled with size 2 blocks
        }
        else
        {
            N = N / 2;
            int[] arr = new int[N];
            arr[0] = 3;
            for(int i = 1; i < N; i++)
            {
                int value = 0;
                for (int j = 0; j < i - 1; j++)
                {
                    value += 2 * arr[j];
                }
                value += 3 * arr[i-1];
                value += 2;
                arr[i] = value;
            }
            System.out.println(arr[N-1]);
        }
    }
}
