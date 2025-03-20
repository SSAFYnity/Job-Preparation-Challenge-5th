import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp = new int[31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp[0] = 1;
        for(int i = 2; i < N+1; i+=2){
            int temp = 0;
            for(int j = 0; j <= i - 4; j++){
                temp += dp[j];
            }
            dp[i] = 3 * dp[i - 2] + 2 * temp;
        }
        System.out.println(dp[N]);
    }
}