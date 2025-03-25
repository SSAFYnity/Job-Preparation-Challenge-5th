import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N;             // 건물의 수
	static int[] building;    // 각 건물의 높이를 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 건물의 수 입력 받기
		building = new int[N];
		
		// 각 건물의 높이를 배열에 저장
		for (int i = 0; i < N; i++) {
			building[i] = Integer.parseInt(br.readLine());
		}

		long cnt = 0; // 총 볼 수 있는 건물쌍의 수
		Stack<Integer> stack = new Stack<>(); // 스택을 이용하여 오른쪽에서 볼 수 있는 건물 높이를 관리

		// 왼쪽에서 오른쪽으로 건물을 탐색
		for (int i = 0; i < N; i++) {
			// 현재 건물보다 작거나 같은 건물은 스택에서 제거
			// -> 현재 건물이 그보다 높기 때문에, 그 이후 건물에서는 이 작은 건물을 볼 수 없음
			while (!stack.isEmpty() && stack.peek() <= building[i]) {
				stack.pop();
			}

			// 스택에 남아있는 건물은 현재 건물보다 큰 건물들
			// 현재 건물에서는 이 큰 건물들을 오른쪽에서 볼 수 있음
			cnt += stack.size(); // 현재 건물에서 볼 수 있는 건물의 수를 더함

			// 현재 건물의 높이를 스택에 추가
			stack.add(building[i]);
		}

		// 결과 출력: 총 볼 수 있는 건물쌍의 수
		System.out.println(cnt);
	}
}
