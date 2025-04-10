import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] remainTime;
    static int[] tasks;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        remainTime = new int[M];
        tasks = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            tasks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tasks);
        for(int i = N-1; i >= 0; i--){
            int nowJob = tasks[i];
            Result r = canPut();
            if(!r.find){
                result += remainTime[r.idx];
                timeCalc(remainTime[r.idx]);
                remainTime[r.idx] = nowJob;
            }
            else {
                remainTime[r.idx] = nowJob;
            }
        }
        remainTimeCalc();
        System.out.println(result);
    }

    static void remainTimeCalc() {
        int maxTime = 0;
        for(int i = 0; i < M; i++){
            maxTime = Math.max(maxTime, remainTime[i]);
        }
        result += maxTime;
    }

    static void timeCalc(int time) {
        for(int i = 0; i < M; i++){
            remainTime[i] -= time;
        }
    }

    static Result canPut() {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0; i < M; i++){
            if(min > remainTime[i]){
                min = remainTime[i];
                minIndex = i;
            }
            if(remainTime[i] == 0){
                return new Result(i, 0, true);
            }
        }
        return new Result(minIndex, min, false);
    }

    static class Result {
        int idx;
        int minValue;
        boolean find;

        Result(int idx, int minValue, boolean find) {
            this.idx = idx;
            this.minValue = minValue;
            this.find = find;
        }
    }
}