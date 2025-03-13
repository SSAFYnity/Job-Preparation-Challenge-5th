import java.util.StringTokenizer;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
    // 상하좌우
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int n;       // 2 <= 격자의 크기 <= 20
    static int m;       // 1 <= 승객의 수 <= n^2
    static int[][] arr;
    static int answer;      // 모든 손님을 이동시키고 연료를 충전했을 때 남은 연료의 양
    static int taxiX;
    static int taxiY;
    static int[][] guest;       // 각 승객의 출발지, 목적지를 저장
    static PriorityQueue<Passenger> pq;
    static boolean isAble;
    static boolean[] isComplete;

    static class Passenger implements Comparable<Passenger> {
        int id;     // 승객 인덱스 번호
        int x;      // 행
        int y;      // 열
        int distance;       // 택시와의 거리

        Passenger(int id, int x, int y, int distance) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Passenger passenger) {
            if(this.distance != passenger.distance) {      // 거리가 가까운 순
                return Integer.compare(this.distance, passenger.distance);
            }
            if(this.x != passenger.x) {
                return Integer.compare(this.x, passenger.x);        // 행 번호가 작은 순
            }
            return Integer.compare(this.y, passenger.y);       // 열 번호가 작은 순
        }
    }

    public static int getDistance(int x, int y) {
        Queue<int[]> que = new ArrayDeque();
        que.offer(new int[]{taxiX, taxiY});
        boolean[][] visit = new boolean[n + 1][n + 1];
        int distance = -1;
        boolean isEnd = false;
        while(!que.isEmpty() && !isEnd) {
            int queSize = que.size();
            for(int i=0; i<queSize; i++) {
                int[] cur = que.poll();
                if (cur[0] == x && cur[1] == y) {      // 도착했다면
                    isEnd = true;
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = DX[d] + cur[0];
                    int ny = DY[d] + cur[1];
                    // 좌표가 유효하지 않거나 이미 방문했거나 벽이라면 건너뛰기
                    if (!isValid(nx, ny) || visit[nx][ny] || arr[nx][ny] == 1) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    que.offer(new int[]{nx, ny});
                }
            }
            distance++;     // 이동 거리
        }
        if(!isEnd) {
            return -1;
        }
        return distance;
    }

    public static boolean isValid(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    /*
        (x, y) = 택시 기사의 좌표
     */
    public static boolean bfs(int passengerX, int passengerY, boolean isArrive) {
        Queue<int[]> que = new ArrayDeque();
        que.offer(new int[]{taxiX, taxiY});
        boolean[][] visit = new boolean[n + 1][n + 1];
        int distance = 0;
        while(!que.isEmpty()) {
            int queSize = que.size();
            for(int i=0; i<queSize; i++) {
                int[] cur = que.poll();
                if (cur[0] == passengerX && cur[1] == passengerY) {      // 도착했다면
                    taxiX = passengerX;
                    taxiY = passengerY;
                    if(isArrive) {      // 목적지에 도착하면
                        if(answer - distance < 0) {
                            return false;
                        }
                        answer = answer - distance + (distance * 2);
                    } else {
                        answer -= distance;
                    }
                    return true;
                } else if(distance > answer) {       // 연료가 바닥났다면
                    return false;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = DX[d] + cur[0];
                    int ny = DY[d] + cur[1];
                    // 좌표가 유효하지 않거나 이미 방문했거나 벽이라면 건너뛰기
                    if (!isValid(nx, ny) || visit[nx][ny] || arr[nx][ny] == 1) {
                        continue;
                    }
                    visit[nx][ny] = true;
                    que.offer(new int[]{nx, ny});
                }
            }
            distance++;     // 이동 거리
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = Integer.parseInt(st.nextToken());      // 1 <= 초기연료 <= 500,000
        arr = new int[n + 1][n + 1];
        isAble = true;
        isComplete = new boolean[m];
        // 지도 입력 받기(0 = 빈칸, 1 = 벽)
        for(int x=1; x<=n; x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=1; y<=n; y++) {
                arr[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        // 백준이 운전을 시작하는 칸의 행 번호와 열 번호 입력 받기
        st = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(st.nextToken());
        taxiY = Integer.parseInt(st.nextToken());
        // 각 승객의 출발지의 행과 열 번호, 목적지의 행과 열 번호 입력 받기
        guest = new int[m][4];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            guest[i][0] = Integer.parseInt(st.nextToken());     // 출발지 행
            guest[i][1] = Integer.parseInt(st.nextToken());     // 출발지 열
            guest[i][2] = Integer.parseInt(st.nextToken());     // 목적지 행
            guest[i][3] = Integer.parseInt(st.nextToken());     // 목적지 열
        }

        // 모든 승객을 다 목적지까지 이동시킬 때까지 탐색
        for(int i=0; i<m; i++) {
            // 택시와 승객간의 거리 계산
            PriorityQueue<Passenger> pq = new PriorityQueue();
            for(int j=0; j<m; j++) {
                if(isComplete[j]) {     // 목적지까지 도착한 승객은 건너뛰기
                    continue;
                }
                pq.add(new Passenger(j, guest[j][0], guest[j][1], getDistance(guest[j][0], guest[j][1])));
            }

            Passenger passenger = pq.poll();
            if(passenger.distance == -1) {
                isAble = false;
                break;
            }
            int id = passenger.id;
            isComplete[id] = true;
            // 승객을 태우기 위해 출발지까지 이동
            answer -= passenger.distance;
            // 택시의 위치는 승객의 출발점으로 갱신
            taxiX = guest[id][0];
            taxiY = guest[id][1];
            // 승객을 목적지까지 이동
            if(!bfs(guest[id][2], guest[id][3], true)) {
                isAble = false;
                break;
            }
        }

        if(!isAble) {
            answer = -1;
        }

        System.out.println(answer);       // 이동 도중에 연료가 바닥나거나 모든 손님을 이동시키지 못하면 -1
    }
}