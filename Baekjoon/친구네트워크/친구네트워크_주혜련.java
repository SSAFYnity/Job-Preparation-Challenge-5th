import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int TC, N;
    private static int[] cnt, parent;
    private static HashMap<String, Integer> map=new HashMap<>();

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        TC=Integer.parseInt(st.nextToken());

        while(TC-->0){
            //init
            st = new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            int Idx=1;
            parent=new int[2*N+1];
            cnt=new int[2*N+1];
            for(int i=1;i<=2*N;i++){
                parent[i]=i; cnt[i]=1;
            }

            //solve
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                String a=st.nextToken();
                String b=st.nextToken();
                if(!map.containsKey(a)) map.put(a,Idx++);
                if(!map.containsKey(b)) map.put(b,Idx++);

                int aIdx=map.get(a);
                int bIdx=map.get(b);
                int count=merge(aIdx,bIdx);
                System.out.println(count);
            }
        }


    }

    private static int find(int u){
        if(parent[u]==u) return u;
        return parent[u]=find(parent[u]);
    }

    private static int merge(int u,int v){
        u=find(u);
        v=find(v);
        if(u==v) return cnt[u];
        parent[u]=v;
        return cnt[v]+=cnt[u];
    }

}
