import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());  // C개 열
        int P = Integer.parseInt(st.nextToken());  // 블록 번호 P
        int[] heights = new int[C];  // 각 칸의 높이를 담을 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 7; i++) {
            map.put(i, 0);
        }

        map.put(1, C);

        int one = heights[0];
        int two = heights[1];

        if (one == two) {
            map.put(2, map.get(2)+ 1);
            map.put(6, map.get(6)+ 1);
            map.put(7, map.get(7)+ 1);
        }
        if (one - two == 1) {
            map.put(3, map.get(3)+ 1);
            map.put(5, map.get(5)+ 1);
        }
        if (one - two == -1) {
            map.put(4, map.get(4)+ 1);
            map.put(5, map.get(5)+ 1);
        }
        if (one - two == 2) {
            map.put(6, map.get(6)+ 1);
        }
        if (one - two == -2) {
            map.put(7, map.get(7)+ 1);
        }

        for (int i = 2; i < C; i++) {
            int ch = heights[i];
            if (one == two && two == ch) {
                map.put(5, map.get(5)+ 1);
                map.put(6, map.get(6)+ 1);
                map.put(7, map.get(7)+ 1);
            }
            if (two == ch) {
                map.put(2, map.get(2)+ 1);
                map.put(6, map.get(6)+ 1);
                map.put(7, map.get(7)+ 1);
            }

            if (two - ch == 1) {
                map.put(3, map.get(3)+ 1);
                map.put(5, map.get(5)+ 1);
            }
            if (two - ch == -1) {
                map.put(4, map.get(4)+ 1);
                map.put(5, map.get(5)+ 1);
            }
            if (two - ch == 2) {
                map.put(6, map.get(6)+ 1);
            }
            if (two - ch == -2) {
                map.put(7, map.get(7)+ 1);
            }

            if (one == two && ch - two == 1) {
                map.put(3, map.get(3)+ 1);
            }
            if (one - two == 1 && ch == two) {
                map.put(4, map.get(4)+ 1);
            }
            if (one - two == -1 && two == ch) {
                map.put(6, map.get(6)+ 1);
            }
            if (one == two && two - ch == 1) {
                map.put(7, map.get(7)+ 1);
            }

            if (one == ch && one - two == 1) {
                map.put(5, map.get(5)+ 1);
            }

            if (i < C-1) {
                if (one == two && one == ch && one == heights[i + 1]) {
                    map.put(1, map.get(1) + 1);
                }
            }

            one = two;
            two = ch;
        }
    System.out.println(map.get(P));

    }
}
