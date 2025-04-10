import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N;
	static char[][] box;
	static Position b, e;
	static boolean[][][] visited;
	static int[][] way = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		box = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				box[i][j] = str.charAt(j);
			}
		}

		b = find('B');
		e = find('E');

		visited = new boolean[N][N][2];
		Queue<int[]> queue = new ArrayDeque<>(); // x, y, w
		queue.add(new int[] { b.sx, b.sy, b.w, 0 }); // 통나무 위치..

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0] == e.sx && now[1] == e.sy && now[2] == e.w) {
				System.out.println(now[3]);
				return;
			}
			for (int w = 0; w < 4; w++) { // 통나무 중간 옮기기
				int nx = now[0] + way[w][0];
				int ny = now[1] + way[w][1];
				// nx, ny, 통나무위치(w)
				if (isMoveValid(nx, ny, now[2]) && !visited[nx][ny][now[2]]) {
					visited[nx][ny][now[2]] = true;
					queue.add(new int[] { nx, ny, now[2], now[3] + 1 });
				}
			}
			if (isRotateValid(now[0], now[1]) && !visited[now[0]][now[1]][1 - now[2]]) {
				visited[now[0]][now[1]][1 - now[2]] = true;
				queue.add(new int[] { now[0], now[1], 1 - now[2], now[3] + 1 });
			}
		}
		System.out.println(0);
	}

	static boolean isRotateValid(int x, int y) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int nx = x + i;
				int ny = y + j;
				if (!inRange(nx, ny) || box[nx][ny] == '1') {
					return false;
				}
			}
		}
		return true;
	}

	static boolean isMoveValid(int x, int y, int w) {
		int nx = x + way[w][0];
		int ny = y + way[w][1];
		int nx2 = x - way[w][0];
		int ny2 = y - way[w][1];
		return inRange(x, y) && inRange(nx, ny) && inRange(nx2, ny2) && box[x][y] != '1' && box[nx][ny] != '1'
				&& box[nx2][ny2] != '1';
	}

	static Position find(char x) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j] == x) {
					for (int w = 0; w < 2; w++) {
						int nx = i + way[w][0];
						int ny = j + way[w][1];
						int nx2 = i + 2 * way[w][0];
						int ny2 = j + 2 * way[w][1];
						if (inRange(nx, ny) && box[nx][ny] == x && inRange(nx2, ny2) && box[nx2][ny2] == x) {
							return new Position(nx, ny, w);
						}
					}
				}
			}
		}
		return null;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Position {
		int sx, sy, w; // 중간 지점

		Position(int sx, int sy, int w) {
			this.sx = sx;
			this.sy = sy;
			this.w = w;
		}

		@Override
		public String toString() {
			return "(" + this.sx + "," + this.sy + ") " + this.w;
		}
	}

}
