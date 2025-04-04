import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		// M개의 콘센트를 사용하여
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// N개의 전자기기를 모두 충전하는 데 걸리는 시간
		for (int i = arr.length - 1; i >= 0; i--) {
			if (pq.size() < M) {
				pq.add(arr[i]);

			} else {
				int time = pq.poll();
				pq.add(time + arr[i]);
			}
		}
		int minTime = 0;
		while (!pq.isEmpty()) {
			minTime = pq.poll();
		}
		System.out.println(minTime);
	}
}