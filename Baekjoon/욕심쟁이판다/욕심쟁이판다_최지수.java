import java.util.*;
import java.io.*;

public class Main {
    static int n, answer;
    static int[][] arr, visit;

    static int[][] dr = {{0,0,-1,1}, {1,-1,0,0}};

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static int back(int y, int x) {
        /*
        갈 수 있는 곳으로 간다 | 더 이상 갈 수 없으면 현재 칸에 1을 저장하고 반환한다

        미리 와본 곳(visit이 0이 아닌 곳)이면 visit값을 반환한다

        (다음 호출의 반환값 + 1)을 이 칸의 visit에 남긴다
         */
        if (visit[y][x] != 0) return visit[y][x];

        int temp = 0;
        for (int i = 0; i < 4; i++) {
            int w = y + dr[0][i];
            int v = x + dr[1][i];
            if (arr[w][v] <= arr[y][x]) continue;
            temp = Math.max(back(w,v), temp);
        }

        return visit[y][x] = temp + 1;
    }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        arr = new int[n+2][n+2];
        visit = new int[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer = Math.max(answer, back(i, j));
            }
        }

        System.out.println(answer);
    }
}

