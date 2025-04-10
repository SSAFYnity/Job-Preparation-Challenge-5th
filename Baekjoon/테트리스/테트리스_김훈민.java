import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C, P;
    static int[] maxHeight;
    static String[][] blocks = {
            {"0", "0000"},
            {"00"},
            {"10", "001"},
            {"01", "100"},
            {"000", "101", "01", "10"},
            {"000", "20", "011", "00"},
            {"000", "02", "110", "00"}
    };
    static String[] useBlock;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        useBlock = blocks[P-1];
        maxHeight = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            maxHeight[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        for(String block : useBlock) {
            for(int i = 0; i < C - block.length() + 1; i++){
                if(canPut(i, block)){
                    result++;

                }
            }
        }
        System.out.println(result);
    }

    static boolean canPut(int sPoint, String block) {
        int[] tempArr = new int[block.length()];
        for(int i = 0; i < block.length(); i++){
            tempArr[i] = maxHeight[i + sPoint] - (block.charAt(i) - '0');
        }
        int start = tempArr[0];
        for(int t : tempArr){
            if(start != t)
                return false;
        }
        return true;
    }

}
