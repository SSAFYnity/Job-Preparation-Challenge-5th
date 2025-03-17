import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[][] box;
	static int K;

	static int EMPTY = 0, SNAKE = 1, APPLE = 2;
	static int L; // 뱀의 방향 변환 횟수
	static Snake snake;
	static int[][] way = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static Map<Integer, Integer> directions = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드의 크기
		box = new int[N][N];
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		for (int k = 0; k < K; k++) { // 사과의 위치
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 행
			int c = Integer.parseInt(st.nextToken()) - 1; // 열
			box[r][c] = APPLE;
		}
		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수

		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // time 초 후에
			int d = st.nextToken().charAt(0) == 'L' ? 3 : 1; // 어떤 방향으로 회전
			int t = 0;
			directions.put(x, d);
		}

		int time = 0;
		snake = new Snake(0, 0, 0);
		box[0][0] = SNAKE;
		while (true) {

			// t초 후에 왼쪽 혹은 오른쪽 90도 방향을 회전시킨다
			int nx = snake.x + way[snake.w][0];
			int ny = snake.y + way[snake.w][1];
			time += 1;

			if (!inRange(nx, ny) || box[nx][ny] == SNAKE) { // 0. 벽이나 자기 자신의 몸과 부딪히는 경우
				break;
			}

			snake.body.addFirst(new int[] { nx, ny });

			if (box[nx][ny] != APPLE) { // 2. 사과가 없다면
				int[] tail = snake.body.pollLast(); // 꼬리 칸 없애기
				box[tail[0]][tail[1]] = EMPTY; // 그 칸의 사과가 없어짐
			}
			box[nx][ny] = SNAKE; // 1. 머리 추가
			snake.x = nx;
			snake.y = ny;

			// 방향 전환
			if (directions.containsKey(time)) {
				snake.w = (snake.w + directions.get(time)) % 4;
			}
		}
		System.out.println(time);

	}

	static void printSnake() {
		System.out.println(snake);
	}

	static void printBox() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(box[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Snake {
		int x, y, w;
		Deque<int[]> body;

		Snake(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.body = new ArrayDeque<>();
			this.body.add(new int[] { x, y });
		}

		@Override
		public String toString() {
			return "[" + this.x + "," + this.y + " " + this.w + " >> " + this.body + "]";
		}
	}

}
