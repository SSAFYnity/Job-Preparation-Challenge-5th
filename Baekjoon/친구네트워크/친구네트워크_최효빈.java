package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int MAX_FRIENDS = 2 * 100000 + 1;
    static int[] parents;

    static Map<String, Integer> idMap;
    static int[] sizes;
    static int currId;

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        while(TC-- > 0){
            test();
        }

        System.out.print(sb);
    }

    static void test() throws IOException{

        currId = 0;
        int F = Integer.parseInt(br.readLine());
        parents = new int[MAX_FRIENDS];
        sizes = new int[MAX_FRIENDS];
        idMap  = new HashMap<>();

        while(F-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String userA = st.nextToken();
            String userB = st.nextToken();

            int idA = enrollAndGetId(userA);
            int idB = enrollAndGetId(userB);
            sb.append(unionAndReturnSize(idA, idB)).append('\n');
        }
    }

    static int enrollAndGetId(String name){
        Integer id = idMap.get(name);
        if(id != null)
            return id;

        idMap.put(name, ++currId);
        parents[currId] = currId;
        sizes[currId] = 1;
        return currId;
    }

    static int find(int id){
        if(parents[id] == id)
            return id;

        return parents[id] = find(parents[id]);
    }

    static int unionAndReturnSize(int idA, int idB){
        int parentA = find(idA);
        int parentB = find(idB);

        if(parentA == parentB)
            return sizes[parentB];

        parents[parentA] = parentB;
        return sizes[parentB] = sizes[parentA] + sizes[parentB];
    }
}
