import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Math.min;


public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] arr, dp;
    private static int N;
    private static int[][] way={{-1,0},{0,1},{1,0},{0,-1}};


    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        arr=new int[501][501];
        dp=new int[501][501];

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<=N;i++)for(int j=0;j<=N;j++) dp[i][j]=-1;

        int ans=0;
        for(int i=0;i<N;i++)for(int j=0;j<N;j++){
            int ret=solve(i,j);
            ans=max(ans,ret);
        }
        System.out.println(ans+1);

    }

    private static int solve(int y,int x){
        int ret=dp[y][x];
        if(ret!=-1) return ret;

        ret=0;
        for(int w=0;w<4;w++){
            int ny=y+way[w][0], nx=x+way[w][1];
            if(ny>=0&&nx>=0&&ny<N&&nx<N&&arr[y][x]<arr[ny][nx]){
                ret=max(ret,solve(ny,nx)+1);
            }
        }
        return dp[y][x]=ret;
    }


}
