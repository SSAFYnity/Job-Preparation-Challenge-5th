import java.io.*;
import java.util.*;

class Main {
    static Map<String, String> parent;  // 부모를 나타내는 맵
    static Map<String, Integer> size;   // 각 집합의 크기를 나타내는 맵

    // find 연산: 부모를 찾아주는 함수
    public static String find(String x) {
        if (parent.get(x).equals(x)) {
            return x;
        }
        parent.put(x, find(parent.get(x)));  // 경로 압축
        return parent.get(x);
    }

    // union 연산: 두 집합을 합치는 함수
    public static void union(String x, String y) {
        String rootX = find(x);
        String rootY = find(y);

        if (!rootX.equals(rootY)) {
            // 작은 트리를 큰 트리 밑으로 합친다 (Union by Rank)
            if (size.get(rootX) > size.get(rootY)) {
                parent.put(rootY, rootX);
                size.put(rootX, size.get(rootX) + size.get(rootY));
            } else {
                parent.put(rootX, rootY);
                size.put(rootY, size.get(rootX) + size.get(rootY));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());  // 테스트 케이스의 개수
        for (int tc = 0; tc < t; tc++) {
            int f = Integer.parseInt(br.readLine());  // 친구 관계의 수
            parent = new HashMap<>();
            size = new HashMap<>();

            for (int i = 0; i < f; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                // name1, name2를 각각 부모로 설정하고, 크기를 1로 초기화
                if (!parent.containsKey(name1)) {
                    parent.put(name1, name1);
                    size.put(name1, 1);
                }
                if (!parent.containsKey(name2)) {
                    parent.put(name2, name2);
                    size.put(name2, 1);
                }

                // 두 친구를 같은 집합으로 합침
                union(name1, name2);

                // 같은 집합에 속한 친구들의 수 출력
                String root = find(name1);
                bw.write(size.get(root) + "\n");
            }
        }
        bw.flush();
    }
}