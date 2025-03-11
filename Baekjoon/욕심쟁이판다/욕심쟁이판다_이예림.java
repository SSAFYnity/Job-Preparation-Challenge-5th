import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1937 {
	static int N;
	static int[][] vs, map;
	static int[] di = {1,0,-1,0}, dj = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		vs = new int[N][N];
		map = new int[N][N];
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				vs[i][j] = -1;
			}
		}
		
		int res = 0;
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				res = Math.max(res, dfs(i, j));
			}
		}
		
		System.out.println(res);
	}
	
	static int dfs(int ci, int cj) {
		if(vs[ci][cj] != -1)
			return vs[ci][cj];
		
		vs[ci][cj] = 1;
		for(int d = 0;d<4;d++) {
			int ni = ci+di[d], nj = cj+dj[d];
			
			if(ni<0||ni>=N||nj<0||nj>=N||map[ni][nj] <= map[ci][cj])
				continue;
			
			vs[ci][cj] = Math.max(vs[ci][cj], dfs(ni, nj)+1);
		}
		return vs[ci][cj];
	}

}
