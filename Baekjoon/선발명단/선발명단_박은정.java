import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int C;
	static int[][] box;
	static boolean[] selected;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		C = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

		for (int c = 0; c < C; c++) {
			box = new int[11][11]; // i번 선수 -> j번 포지션
			for (int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					box[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			selected = new boolean[11]; // 해당 포지션 이미 선택함
			ans = 0;
			// 선수마다 포지션 선택
			back(0, 0);
			System.out.println(ans);

		}

	}

	static void back(int personId, int sum) {

		if (personId == 11) {
			ans = Math.max(sum, ans);
			return;
		}
		for (int posId = 0; posId < 11; posId++) {
			if (selected[posId])
				continue;
			if (box[personId][posId] != 0) {
				selected[posId] = true;
				back(personId + 1, sum + box[personId][posId]);
				selected[posId] = false;
			}
		}
	}

}
