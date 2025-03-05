import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int T, F;
	static Map<String, Integer> toId;
	static int[] parents;
	static int[] subPplCnt; // x가 루트일 때 해당 집합에 포함된 사람의 수 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			parents = new int[F * 2];
			subPplCnt = new int[F * 2];
			for (int f = 0; f < F * 2; f++) {
				parents[f] = f; // 각 노드가 자신을 부모로 가짐
				subPplCnt[f] = 1; // 해당 집합에 포함되는 네트워크 크기 1
			}

			toId = new HashMap<>();
			for (int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String name2 = st.nextToken();
				if (!toId.containsKey(name)) {
					toId.put(name, toId.size());
				}
				if (!toId.containsKey(name2)) {
					toId.put(name2, toId.size());
				}
				int a = toId.get(name);
				int b = toId.get(name2);
				System.out.println(union(a, b));
			}
		}
	}

	static int union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parents[b] = a; // b의 부모를 a로 칭했기 때문에
			subPplCnt[a] += subPplCnt[b]; // b의 서브네트워크에 있는 사람들의 수를 a에 더한다 
			subPplCnt[b] = 1; // b는 더이상 루트가 아니므로 subPplCnt을 초기화한다 
		}
		return subPplCnt[a];
	}

	static int find(int x) {
		if (x == parents[x]) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}

}
