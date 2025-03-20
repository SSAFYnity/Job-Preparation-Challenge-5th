package graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물병_최효빈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] capacities = {A, B, C};
        boolean[] visited = new boolean[createKey(A, B, C) + 1];
        boolean[] answers = new boolean[200 + 1];

        visited[createKey(0, 0, C)] = true;
        answers[C] = true;

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0, C});

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int a = curr[0];
            int b = curr[1];
            int c = curr[2];
            if(a == 0)
                answers[c] = true;

            for(int from = 0; from < 3; from++){
                if(curr[from] == 0)
                    continue;
                for(int to = 0; to < 3; to++){
                    if(from == to)
                        continue;
                    int[] copy = {a, b, c};
                    int remaining = copy[from];
                    int required = capacities[to] - copy[to];

                    int pour = Math.min(remaining, required);
                    copy[from] -= pour;
                    copy[to] += pour;

                    int key = createKey(copy[0], copy[1], copy[2]);
                    if(!visited[key]){
                        visited[key] = true;
                        que.offer(copy);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 200; i++){
            if(answers[i])
                sb.append(i).append(' ');
        }

        System.out.println(sb);
    }

    static int createKey(int a, int b, int c){
        return a * 100_000 + b * 100 + c;
    }
}
