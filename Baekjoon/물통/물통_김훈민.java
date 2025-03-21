import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static boolean[][][] visited;
    static int maxA, maxB, maxC;
    static Set<Integer> results = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());
        visited = new boolean[maxA+1][maxB+1][maxC+1];
        dfs(0, 0, maxC);
        StringBuilder sb = new StringBuilder();
        for(int r : results) sb.append(r).append(" ");
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

    static void dfs(int a, int b, int c) {
        if(visited[a][b][c]) return;
        visited[a][b][c] = true;
        if(a == 0) results.add(c);
        int CtoA = Math.min(maxA - a, c);
        int CtoB = Math.min(maxB - b, c);
        int AtoC = Math.min(maxC - c, a);
        int AtoB = Math.min(maxB - b, a);
        int BtoC = Math.min(maxC - c, b);
        int BtoA = Math.min(maxA - a, b);
        dfs(a + CtoA, b, c - CtoA);
        dfs(a, b + CtoB, c - CtoB);
        dfs(a - AtoC, b, c + AtoC);
        dfs(a - AtoB, b + AtoB, c);
        dfs(a, b - BtoC, c + BtoC);
        dfs(a + BtoA, b - BtoA, c);
    }
}
