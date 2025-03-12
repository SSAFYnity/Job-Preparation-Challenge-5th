import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static int N = 101;
	static int[][] way = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int[][] box = new int[N][N];
		for (int[] rect : rectangle) {
			int sx = rect[0] * 2;
			int sy = rect[1] * 2;
			int ex = rect[2] * 2;
			int ey = rect[3] * 2;
			for (int x = sx; x <= ex; x++) {
				for (int y = sy; y <= ey; y++) {
					if (x == sx || x == ex || y == sy || y == ey) { // 테두리인 경우
						if (box[x][y] == 0) {
							box[x][y] = 1; // 1로 표시
						}
					} else { // 테두리가 아닌 경우
						box[x][y] = 2;
					}
				}
			}
		}

		int path = moveToGet(box, characterX * 2, characterY * 2, itemX * 2, itemY * 2);
		return path;
	}

	static int moveToGet(int[][] box, int characterX, int characterY, int itemX, int itemY) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { characterX, characterY, 0 });
		visited[characterX][characterY] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (now[0] == itemX && now[1] == itemY) {
				return now[2]/2;
			}
			for (int w = 0; w < 4; w++) {
				int nx = now[0] + way[w][0];
				int ny = now[1] + way[w][1];
				if (!inRange(nx, ny))
					continue;
				if (visited[nx][ny])
					continue;
				if (box[nx][ny] == 1) { // 테두리인 경우만 따라 움직인다
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny, now[2] + 1 });
				}
			}
		}
		return 0;
	}

	static boolean inRange(int x, int y) {
		return x >= 0 && x <  N && y >= 0 && y <  N;
	}

}
