package Baekjoon.뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 뱀_김범규 {
    static int N, K, L;
    static int[][] map;
    static Deque<Node> dq = new ArrayDeque<>();
    // 0 우 1 하 2 좌 3 상
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };
    static HashMap<Integer, String> hash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 사과
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            hash.put(x, c);
        }

        startGame();
    }

    public static void startGame() {
        int cr = 0;
        int cc = 0;
        int time = 0;
        int dir = 0;

        dq.add(new Node(cr, cc, dir));

        while(true){
            time++;

            int nr = cr + dr[dir];
            int nc = cc + dc[dir];

            if(checkArea(nr, nc)){
                break;
            }

            //사과를 먹으면
            if(map[nr][nc] == 1){
                map[nr][nc] = 0;
                dq.addFirst(new Node(nr, nc, dir));
            }
            else {
                dq.addFirst(new Node(nr, nc, dir));
                dq.removeLast();
            }

            if(hash.containsKey(time)){
                //방향이 오른쪽 회전
                if(hash.get(time).equals("D")){
                    dir += 1;
                    if(dir == 4){
                        dir = 0;
                    }
                }
                else {
                    dir -= 1;
                    if(dir == -1){
                        dir = 3;
                    }
                }
            }

            cr = nr;
            cc = nc;
        }

        System.out.println(time);
    }

    //범위 벗어나는지 확인
    public static boolean checkArea(int r, int c){
        if(r < 0 || r >= N || c < 0 || c >= N){
            return true;
        }

        for(Node node: dq){
            if(r == node.r && c == node.c){
                return true;
            }
        }

        return false;
    }

    public static class Node {
        int r;
        int c;
        int dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}
