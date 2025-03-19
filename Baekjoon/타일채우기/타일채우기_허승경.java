/*
 * 아이디어 : dp
 * 1. n이 홀수일 때는 채우지 못함
 * 2. n이 짝수일 때는 조합해서 n 만들기?
 * */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n%2 != 0) {
			System.out.println(0);
		}
		int [] dp = new int[31];
		dp[2] = 3;
		dp[4] = 11;
		
		for(int i = 6; i <= n; i++) {
			dp[i] = dp[i-2]*dp[i-4];
		}
		
		System.out.println(dp[n]);

	}

}
