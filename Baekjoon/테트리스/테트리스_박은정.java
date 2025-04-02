import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static int C, P;
	static Map<Integer, List<int[]>> blocks = new HashMap<>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // C열
		P = Integer.parseInt(st.nextToken()); // 블록의 번호
		init();

		int[] height = new int[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		List<int[]> shapes = getBlockById(P);
		for (int[] shape : shapes) {
			for (int hIdx = 0; hIdx < C - shape.length + 1; hIdx++) {
				boolean isDropped = true;
				int base = height[hIdx] - shape[0]; // 시작 높이와 shape[0]을 통해 base 높이 구하기
				for (int shapeIdx = 0; shapeIdx < shape.length; shapeIdx++) {
					int i = hIdx + shapeIdx;
					if (height[i] != base + shape[shapeIdx]) {
						isDropped = false;
						break;
					}
				}
				ans += isDropped ? 1 : 0;
			}
		}
		System.out.println(ans);
	}

	static void init() {
		blocks.put(1, List.of(new int[] { 0 }, new int[] { 0, 0, 0, 0 }));
		blocks.put(2, List.of(new int[] { 0, 0 }));
		blocks.put(3, List.of(new int[] { 0, 0, 1 }, new int[] { 1, 0 }));
		blocks.put(4, List.of(new int[] { 1, 0, 0 }, new int[] { 0, 1 }));
		blocks.put(5, List.of(new int[] { 0, 0, 0 }, new int[] { 0, 1 }, new int[] { 1, 0 }, new int[] { 1, 0, 1 }));
		blocks.put(6, List.of(new int[] { 0, 0, 0 }, new int[] { 0, 0 }, new int[] { 0, 1, 1 }, new int[] { 2, 0 }));
		blocks.put(7, List.of(new int[] { 0, 0, 0 }, new int[] { 0, 2 }, new int[] { 1, 1, 0 }, new int[] { 0, 0 }));
	}

	static List<int[]> getBlockById(int id) {
		return blocks.get(id);
	}

}