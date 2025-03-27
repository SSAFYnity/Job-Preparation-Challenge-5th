package SSP.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5972 {

    private static class Node {
        int to;
        int weight;

        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M;
    static ArrayList<Node>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            adjList[i] = new ArrayList<>();
        }

        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cows = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cows));
            adjList[to].add(new Node(from, cows));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]
        );
        pq.offer(new int[]{1, 0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int pos = curr[0];
            int weight = curr[1];
            if(pos == N){
                System.out.println(weight);
                break;
            }
            visited[pos] = true;
            for (int i = 0; i < adjList[pos].size(); i++){
                Node next = adjList[pos].get(i);
                if(!visited[next.to])
                    pq.offer(new int[]{next.to, weight + next.weight});
            }
        }
    }
}
