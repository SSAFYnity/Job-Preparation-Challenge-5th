import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long result = 0;
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty() && stack.peek() <= num) {
				stack.pop();
			}
			
			result += stack.size();		
			stack.push(num);		// 나보다 높은 건물만 
		}

		
		System.out.println(result);

	}

}
