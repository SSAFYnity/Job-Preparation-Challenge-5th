import java.util.*;
import java.io.*;

public class Main {
    static int maxi = 0, answer = 0;
    static StringBuilder sb = new StringBuilder();

    static boolean[] position = new boolean[11];

    static class Player {
        int no, pos, abi;
        public Player(int no, int pos, int abi) {
            this.no = no;
            this.pos = pos;
            this.abi = abi;
        }
        public String toString() {
            return "[no: " + no + ", pos: " + pos + ", abi: " + abi + "]";
        }
    }

    static List<Player> list = new ArrayList<>();

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static void back(int no, int p) {
        if (no == 11) {
            maxi = Math.max(answer, maxi);
            return;
        }

        int nextP = p + 11;
        for (int i = p + 1; i < p + 11 && i < list.size(); i++) {
            if (list.get(i).no != no) {
                nextP = i;
                break;
            }
        }

        for (int i = p; i < nextP; i++) {
            if (list.size() <= i || list.get(i).no > no) break;
            if (position[list.get(i).pos]) continue;
            position[list.get(i).pos] = true;
            answer += list.get(i).abi;
            back(no+1, nextP);
            position[list.get(i).pos] = false;
            answer -= list.get(i).abi;
        }
    }

    public static void main(String[] args) throws IOException {

        int T = read();

        for (int tc = 0; tc < T; tc++) {
            for (int no = 0; no < 11; no++) {
                for (int pos = 0; pos < 11; pos++) {
                    int abi = read();
                    if (abi == 0) continue;
                    list.add(new Player(no, pos, abi));
                }
            }

            back(0, 0);
            sb.append(maxi).append("\n");
            Arrays.fill(position, false);
            list.clear();
            answer = 0;
            maxi = 0;
        }

        System.out.println(sb.toString());
    }
}

