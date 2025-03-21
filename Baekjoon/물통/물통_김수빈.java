import java.io.*;
import java.util.*;

public class Main {
    private static int[] pour(int[] current, int from, int to, int[] limits) {
        int[] next =Arrays.copyOf(current, 3);
        int move = Math.min(next[from], limits[to] - next[to]);
        next[from] -= move;
        next[to] += move;
        return next;
    }

    private static Set<Integer> bfs(int[] bottle, int[] limits) {
        Set<Integer> lst = new TreeSet<>();
        boolean[][][] visited= new boolean[201][201][201];
        Queue<int[]> queue = new LinkedList<>();  // 큐
        queue.add(bottle);
        visited[bottle[0]][bottle[1]][bottle[2]] = true;
        while (!queue.isEmpty()) {
            int[] bottles = queue.poll();
            int a = bottles[0], b = bottles[1], c = bottles[2];
            // a가 빈 물통인 경우 c에 추가
            if (a == 0) {
                lst.add(c);
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) {
                        continue;
                    }
                    int[] r = pour(bottles, i, j, limits);
                    if (!visited[r[0]][r[1]][r[2]]) {
                        visited[r[0]][r[1]][r[2]] = true;
                        queue.offer(r);
                    }
                }
            }
        }

        return lst;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);
        Set<Integer> answers = bfs(new int[]{0, 0, C}, new int[]{A, B, C});
        for (int answer : answers) {
            System.out.print(answer + " ");
        }
    }
}
