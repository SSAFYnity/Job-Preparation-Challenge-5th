package combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_3980 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int result;
    static int[][] s;
    static final int SIZE = 11;
    static final int FULL = (int) Math.pow(2, SIZE) - 1;

    public static void main(String[] args) throws IOException {

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(TC-- > 0){
            test();
            sb.append(result);
        }

        System.out.println(sb);
        br.close();
    }

    static void test() throws IOException{
        init();

        // ability, player, position
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (b[0] - a[0])
        );
        StringTokenizer st;
        for (int player = 0; player < SIZE; player++) {
            st = new StringTokenizer(br.readLine());

            for (int position = 0; position < SIZE; position++) {
                s[player][position] = Integer.parseInt(st.nextToken());
                if(s[player][position] > 0){
                    pq.offer(new int[]{
                            s[player][position], 1 << player, 1 << position
                    });
                }
            }
        }

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            if(curr[1] == FULL){
                result = curr[0];
                break;
            }

            for (int player = 0; player < SIZE; player++) {
                if( (curr[1] & (1 << player)) > 0 )
                    continue;
                for (int position = 0; position < SIZE; position++) {
                    if( (curr[2] & (1 << position)) > 0 || s[player][position] == 0)
                        continue;
                    pq.offer(new int[]{
                            curr[0] + s[player][position],
                            curr[1] | (1 << player),
                            curr[2] | (1 << position),
                    });
                }
            }
        }

        pq.clear();
    }

    static void init(){
        s = new int[SIZE][SIZE];
        result = 0;
    }

}
