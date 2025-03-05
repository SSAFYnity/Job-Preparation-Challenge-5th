import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static HashMap<String, Integer> friends;
    static int[] network;
    static int[] p; // 네트워크

    public static void make() {
        int len = 200_001;
        p = new int[len];
        network = new int[len];
        Arrays.fill(network, 1); // 본인들이 들어있으니까 1
        for (int i = 0; i < len; i++) {
            p[i] = i;
        }
    }

    public static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }

    public static int union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb)
            return network[pa];

        network[pa] += network[pb];
        p[pb] = pa;
        return network[pa];
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int tc = Integer.parseInt(st.nextToken());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            make();
            friends = new HashMap<>();
            for (int k = 0; k < n; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                friends.putIfAbsent(f1, friends.size() + 1); // 1~N까지의 친구 번호가 적혀있을 것임
                friends.putIfAbsent(f2, friends.size() + 1);
                System.out.println(union(friends.get(f1), friends.get(f2)));
            }
        }
    }

    public static void go() {

    }
}