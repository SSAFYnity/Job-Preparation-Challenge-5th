import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L;
    static int D = 1;
    static int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] graph;
    static Deque<Point> queue = new LinkedList<>();
    static Set<Point> visited = new HashSet<>();
    static Order[] orders;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
        }
        L = Integer.parseInt(br.readLine());
        orders = new Order[L];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            orders[i] = new Order(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }
        queue.add(new Point(0, 0));
        visited.add(new Point(0, 0));

        int time = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            time++;
            Point cur = queue.peekFirst();
            int nx = cur.x + vector[D][0];
            int ny = cur.y + vector[D][1];

            if(!isIn(nx, ny)){
                System.out.println(time);
                return;
            }

            if(visited.contains(new Point(nx, ny))){
                System.out.println(time);
                return;
            }

            if(graph[nx][ny]) {
                graph[nx][ny] = false;
            } else {
                visited.remove(queue.pollLast());
            }

            queue.addFirst(new Point(nx, ny));
            visited.add(new Point(nx, ny));

            if(count < L && time == orders[count].time){
                chgDir(orders[count].dir);
                count++;
            }
        }
    }

    static void chgDir(char dir){
        if(dir == 'L'){
            D -= 1;
            if(D == -1) D = 3;
        } else {
            D = (D + 1) % 4;
        }
    }

    static boolean isIn(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Order{
        int time;
        char dir;

        public Order(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }


    static class Point{
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
