import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;


public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int N;
    private static long[] dp,sum;


    public static void main(String[] args) throws Exception {
        //input
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());

        //init
        N=Integer.parseInt(st.nextToken());
        dp=new long[3*N+1];
        sum=new long[3*N+1];

        //solve
        long ans=3;
        dp[0]=1;dp[2]=3;
        for(int k=4;k<=N;k++){
            sum[k]=sum[k-2]+dp[k-4];
            dp[k]=dp[k-2]*3+sum[k]*2;
        }
        System.out.println(dp[N]);

    }


}
