import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int F;
    static HashMap<String, Integer> map;
    static int[] parents;
    static int[] networks;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            parents = new int[200001];
            networks = new int[200001];
            setParents();
            Arrays.fill(networks, 1);
            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String from = st.nextToken();
                String to = st.nextToken();
                int fromNum = findNameNum(from);
                int toNum = findNameNum(to);
                int parentFromNum = find(fromNum);
                int parentToNum = find(toNum);
                union(parentFromNum, parentToNum);
                if(parentFromNum != parentToNum) {
                    networks[parentToNum] = networks[parentFromNum] + networks[parentToNum];
                }
                sb.append(networks[parentToNum]).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int findNameNum(String to) {
        int temp = 0;
        if (!map.containsKey(to)) {
            temp = map.size() + 1;
            map.put(to, map.size() + 1);
        } else {
            temp = map.get(to);
        }
        return temp;
    }

    static void union(int from, int to) {
        if(from == to) return;
        parents[from] = to;
    }

    static int find(int n){
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    static void setParents(){
        for(int i = 0; i < 200001; i++) {
            parents[i] = i;
        }
    }
}
