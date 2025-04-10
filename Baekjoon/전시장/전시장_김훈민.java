import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static Paint[] paints;
    static int result;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        paints = new Paint[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            paints[i] = new Paint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(paints);
        dp[0] = paints[0].value;
        result = dp[0];
        for(int i = 1; i < N; i++){
            int findIdx = find(i);
            dp[i] = dp[i-1];
            if(findIdx != -1){
                dp[i] = Math.max(dp[i-1], dp[find(i)] + paints[i].value);
            } else {
                dp[i] = Math.max(dp[i-1], paints[i].value);
            }
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }

    static int find(int idx){
        int start = 0;
        int end = idx - 1;
        int max_idx = -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(paints[idx].height - paints[mid].height >= S){
                max_idx = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return max_idx;
    }

    static class Paint implements Comparable<Paint> {
        int height;
        int value;

        Paint(int height, int value){
            this.height = height;
            this.value = value;
        }

        @Override
        public int compareTo(Paint o) {
            return this.height - o.height;
        }
    }
}
