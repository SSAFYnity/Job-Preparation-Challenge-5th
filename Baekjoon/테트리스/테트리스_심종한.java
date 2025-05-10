import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int C, P, ans;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[C];
        for (int i = 0; i < C; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        switch (P) {
            case 1:
                ans = solve1();
                break;
            case 2:
                ans = solve2();
                break;
            case 3:
                ans = solve3();
                break;
            case 4:
                ans = solve4();
                break;
            case 5:
                ans = solve5();
                break;
            case 6:
                ans = solve6();
                break;
            case 7:
                ans = solve7();
                break;
        }
        System.out.println(ans);
    }

    private static int solve1() {
        int cnt = 0;
        // 1
        cnt += C;
        // 2
        for (int i = 3; i < C; i++) {
            if (arr[i - 3] == arr[i - 2] && arr[i - 2] == arr[i - 1] && arr[i - 1] == arr[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    private static int solve2() {
        int cnt = 0;
        // 1
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] == arr[i])
                cnt++;
        }
        return cnt;
    }

    private static int solve3() {
        int cnt = 0;
        // 1
        for (int i = 2; i < C; i++) {
            if (arr[i - 2] == arr[i - 1] && arr[i - 1] + 1 == arr[i])
                cnt++;
        }
        // 2
        for (int i = 1; i < C; i++) {
            if (arr[i - 1] - 1 == arr[i])
                cnt++;
        }
        return cnt;
    }

    private static int solve4() {
        int cnt = 0;
        // 1
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] - 1 == arr[i - 1] && arr[i - 1] == arr[i])
                cnt++;
        }
        // 2
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] + 1 == arr[i])
                cnt++;
        }
        return cnt;
    }

    private static int solve5() {
        int cnt = 0;
        // 1
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] == arr[i - 1] && arr[i - 1] == arr[i])
                cnt++;
        }
        // 2
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] - 1 == arr[i])
                cnt++;
        }
        // 3
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] - 1 == arr[i - 1] && arr[i - 1] + 1 == arr[i])
                cnt++;
        }
        // 4
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] + 1 == arr[i])
                cnt++;
        }
        return cnt;
    }

    private static int solve6() {
        int cnt = 0;
        // 1
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] == arr[i - 1] && arr[i - 1] == arr[i])
                cnt++;
        }
        // 2
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] - 2 == arr[i])
                cnt++;
        }
        // 3
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] + 1 == arr[i - 1] && arr[i - 1] == arr[i])
                cnt++;
        }
        // 4
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] == arr[i])
                cnt++;
        }
        return cnt;
    }

    private static int solve7() {
        int cnt = 0;
        // 1
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] == arr[i - 1] && arr[i - 1] == arr[i])
                cnt++;
        }
        // 2
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] == arr[i])
                cnt++;
        }
        // 3
        for (int i = 2; i < C; i++)
        {
            if (arr[i - 2] == arr[i - 1] && arr[i - 1] - 1 == arr[i])
                cnt++;
        }
        // 4
        for (int i = 1; i < C; i++)
        {
            if (arr[i - 1] + 2 == arr[i])
                cnt++;
        }
        return cnt;
    }

}
