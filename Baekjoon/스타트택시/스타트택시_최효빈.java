package graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class 스타트택시_최효빈 {

    static int N, M, totalArrival;
    static int answer = -1;

    static int[][] map;
    static int[][] goals;
    static boolean[][] visited;
    static final int WALL = -1;
    static final int EMPTY_ID = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int startFuel = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        goals = new int[M+1][2];

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                if(st.nextToken().equals("1"))
                    map[r][c] = WALL;
            }
        }

        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());

        for (int idx = 1; idx <= M; idx++) {
            st = new StringTokenizer(br.readLine());
            int waitR = Integer.parseInt(st.nextToken());
            int waitC = Integer.parseInt(st.nextToken());
            map[waitR][waitC] = idx;

            int goalR = Integer.parseInt(st.nextToken());
            int goalC = Integer.parseInt(st.nextToken());
            goals[idx][0] = goalR;
            goals[idx][1] = goalC;
        }

        BFS(startR, startC, startFuel);

        System.out.println(answer);
    }

    static void BFS(int startR, int startC, int startFuel){

        // 상, 좌, 우, 하
        int[] dR = {-1, 0, 0, 1};
        int[] dC = {0, -1, 1, 0};

        Queue<Car> que = new ArrayDeque<>();
        visited[startR][startC] = true;
        que.offer(new Car(startR, startC, 0, startFuel, 0));


        while(!que.isEmpty()){
            Car curr = que.poll();
            int r = curr.r;
            int c = curr.c;
            int id = curr.passengerId;
            int fuel = curr.fuel;
            int reward = curr.reward;

            if(id == EMPTY_ID){
                // 빈 택시
                if(map[r][c] > 0){
                    // 탑승
                    id = map[r][c];
                    map[r][c] = 0;
                    visited = new boolean[N+1][N+1];
                    que.clear();
                }
            }else if(r == goals[id][0] && c == goals[id][1]){

                fuel += reward * 2;
                // 승객 태운 택시, 목적지 도착
                if(++totalArrival == M){
                    answer = fuel;
                    break;
                }

                id = EMPTY_ID;
                reward = 0;
                visited = new boolean[N+1][N+1];
                que.clear();
            }

            if(fuel > 0){
                int nr, nc;
                for (int dir = 0; dir < 4; dir++) {
                    nr = r + dR[dir];
                    nc = c + dC[dir];

                    if(isOuttaBound(nr, nc))
                        continue;
                    visited[nr][nc] = true;
                    que.offer(new Car(nr, nc, id, fuel - 1, id != EMPTY_ID ? reward + 1 : reward));
                }
            }
        }
    }

    static boolean isOuttaBound(int r, int c){
        return r < 1 || c < 1 || r > N || c > N || map[r][c] == WALL || visited[r][c];
    }


    private static class Car {
        int r;
        int c;
        int passengerId;
        int fuel;
        int reward;

        public Car(int r, int c, int passengerId, int fuel, int reward){
            this.r = r;
            this.c = c;
            this.passengerId = passengerId;
            this.fuel = fuel;
            this.reward = reward;
        }
    }
}
