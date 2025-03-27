package Baekjoon.택배배송;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 택배배송_김범규 {
    static int N, M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] dist;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        dijkstra();
        System.out.println(dist[N - 1]);

    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next: graph.get(cur.v)){
                //최소가 갱신될 수 있는 상황이라면
                if(dist[next.v] > dist[cur.v] + next.cost){
                    dist[next.v] = dist[cur.v] + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    public static class Node {
        int v;
        int cost;

        public Node(int d, int cost) {
            this.v = d;
            this.cost = cost;
        }
    }
}