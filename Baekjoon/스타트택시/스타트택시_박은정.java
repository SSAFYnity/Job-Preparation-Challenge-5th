import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] way = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M, FUEL;
	static int[][] box;
	static int[][] start;
	static int[][] end;
	static boolean[] isMoved;

	static class Path {
		int id, sx, sy, ex, ey, dist;

		Path(int id, int sx, int sy, int ex, int ey, int dist) {
			this.id = id;
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		FUEL = Integer.parseInt(st.nextToken());
		box = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		start = new int[N][N];
		end = new int[N][N];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;

		Path[] paths = new Path[M + 1];
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			int dist = bfs(sx, sy, ex, ey);
			if (dist == -1) {
				System.out.println(-1);
				return;
			}
			paths[m] = new Path(m, sx, sy, ex, ey, dist);
			start[sx][sy] = m;
			end[ex][ey] = m;
		}

		isMoved = new boolean[M + 1];
		while (true) {
			int[] result = findNearId(r, c);
			int id = result[0];
			int distToPassenger = result[1];

			if (id == -1 || FUEL < distToPassenger) {
				break;
			}

			FUEL -= distToPassenger;
			start[paths[id].sx][paths[id].sy] = 0; // 승객을 찾은 후 제거

			int rideDist = bfs(paths[id].sx, paths[id].sy, paths[id].ex, paths[id].ey);
			if (rideDist == -1 || FUEL < rideDist) {
				break;
			}

			r = paths[id].ex;
			c = paths[id].ey;
			FUEL += rideDist; // 목적지까지 이동 후 연료 충전
			isMoved[id] = true;

			if (isAllMoved()) {
				break;
			}
		}

		System.out.println(isAllMoved() ? FUEL : -1);
	}

	static boolean isAllMoved() {
		for (int i = 1; i < isMoved.length; i++) {
			if (!isMoved[i]) {
				return false;
			}
		}
		return true;
	}

	static int[] findNearId(int sx, int sy) {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<int[]> queue = new PriorityQueue<>(
				Comparator.comparingInt((int[] x) -> x[2]).thenComparingInt(x -> x[0]).thenComparingInt(x -> x[1]));

		queue.add(new int[] { sx, sy, 0 });
		visited[sx][sy] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], dist = now[2];

			if (start[x][y] > 0) {
				return new int[] { start[x][y], dist };
			}

			for (int[] d : way) {
				int nx = x + d[0];
				int ny = y + d[1];

				if (inRange(nx, ny) && !visited[nx][ny] && box[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny, dist + 1 });
				}
			}
		}
		return new int[] { -1, Integer.MAX_VALUE };
	}

	static int bfs(int sx, int sy, int ex, int ey) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { sx, sy, 0 });
		visited[sx][sy] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0] == ex && now[1] == ey)
				return now[2];

			for (int[] d : way) {
				int nx = now[0] + d[0];
				int ny = now[1] + d[1];

				if (inRange(nx, ny) && !visited[nx][ny] && box[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny, now[2] + 1 });
				}
			}
		}
		return -1;
	}
}
