package DP;

import java.io.IOException;
import java.util.Scanner;

public class Main_2133 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if(N % 2 == 0){
            long[] DP = new long[N+1];
            DP[2] = 3;
            for (int i = 4; i <= N; i+=2) {
               DP[i] = 2;
                for (int j = 2; j < i; j += 2) {
                    DP[i] += DP[i-j] * (j == 2 ? 3 : 2);
                }
            }
            System.out.println(DP[N]);
        }else{
            System.out.println(0);
        }
    }
}
