import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int N;
    static int fuel;
    static int M;
    static Point taxi;
    static boolean[][] visited;
    static int[][] vector = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static Queue<Point> queue = new LinkedList<Point>();
    static Point[] destination;
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        visited = new boolean[N][N];
        M = Integer.parseInt(st.nextToken());
        destination = new Point[M + 1];
        fuel = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                // wall
                if (graph[i][j] == 1)
                    graph[i][j] = 500;

            }
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Point(x, y);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            // start marking
            graph[x1][y1] = i + 1;
            // destination save
            destination[i + 1] = new Point(x2, y2);
        }
        int count = 0;
        while(M != count) {
            queue.add(new Point(taxi.x, taxi.y));
            visited[taxi.x][taxi.y] = true;
            int find_idx = find_bfs();
            resetVisited();
            // fail
            if(find_idx == -1) {
                System.out.println(-1);
                return;
            }
            // find
            else {
                queue.add(new Point(taxi.x, taxi.y));
                visited[taxi.x][taxi.y] = true;
                int time = go_bfs(find_idx);
                // fail
                if(time == -1) {
                    System.out.println(-1);
                    return;
                }
                else {
                    fuel += (time * 2);
                }
                resetVisited();
            }
            count++;
        }
        System.out.println(fuel);
    }

    static int go_bfs(int passenger_idx) {
        int time = 0;
        int size;
        int x = -1;
        int y = -1;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if(p.x == destination[passenger_idx].x && p.y == destination[passenger_idx].y) {
                    if(fuel >= time) {
                        fuel -= time;
                        queue.clear();
                        taxi.x = p.x;
                        taxi.y = p.y;
                        return time;
                    }
                    else {
                        return -1;
                    }
                }
                for (int[] v : vector) {
                    int nx = p.x + v[0];
                    int ny = p.y + v[1];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(graph[nx][ny] == 500) continue;
                    if(visited[nx][ny]) continue;
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            time++;
        }
        return -1;
    }

    static int find_bfs() {
        int time = 0;
        int size;
        int passenger_idx = 500;
        int x = 21;
        int y = 21;
        boolean flag = false;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if(graph[p.x][p.y] > 0) {
                    flag = true;
                    if(x >= p.x) {
                        passenger_idx = graph[p.x][p.y];
                        x = p.x;
                        y = p.y;
                        if(y > p.y) {
                            passenger_idx = graph[p.x][p.y];
                            x = p.x;
                            y = p.y;
                        }
                    }
                }
                for (int[] v : vector) {
                    int nx = p.x + v[0];
                    int ny = p.y + v[1];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(graph[nx][ny] == 500) continue;
                    if(visited[nx][ny]) continue;
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            if(flag) {
                if(fuel >= time) {
                    fuel -= time;
                    queue.clear();
                    taxi.x = x;
                    taxi.y = y;
                    graph[x][y] = 0;
                    return passenger_idx;
                }
                else {
                    return -1;
                }
            }
            time++;
        }
        return -1;
    }

    static void resetVisited() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}