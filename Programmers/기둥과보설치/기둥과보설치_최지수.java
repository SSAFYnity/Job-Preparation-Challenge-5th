import java.util.*;

class Solution {
    int n;
    boolean[][] wall;
    boolean valid(int y, int x, boolean hor) {
        if (hor) return horValid(y,x);
        else return verValid(y,x);
    }
    boolean verValid(int y, int x) {
        if (y >= 2*n) return false;
        if (y == 0) return true;
        else if (exist(y-2,x,false)) return true;
        boolean left = exist(y,x-2,true);
        boolean right = exist(y,x,true);
        if (left || right) return true;
        return false;
    }
    boolean horValid(int y, int x) {
        if (x >= 2*n) return false;
        if (y >= 2) {
            if (exist(y-2,x,false)) return true;
            if (exist(y-2,x+2,false)) return true;
        }
        boolean left = exist(y,x-2,true);
        boolean right = exist(y,x+2,true);
        if (left && right) return true;
        return false;
    }
    boolean exist(int y, int x, boolean hor) {
        if (y < 0 || x < 0) return false;
        if (hor) return x <= n*2 - 2 && wall[y][x] && wall[y][x+1];
        else return y <= n*2 - 2 && wall[y][x] && wall[y+1][x];
    }
    void insert(int y, int x, boolean hor) {
        // System.out.println(y/2 + " " + x/2 + " " + (hor?"가로 ":"세로 ") + valid(y,x,hor));
        if (!valid(y,x,hor)) return;
        if (hor) {
            wall[y][x] = true;
            wall[y][x+1] = true;
        } else {
            wall[y][x] = true;
            wall[y+1][x] = true;
        }
    }
    void delete(int y, int x, boolean hor) {
        if (hor) {
            if (!exist(y,x,false)) wall[y][x] = false;
            wall[y][x+1] = false;
            
            boolean verFail = (exist(y,x,false) && !verValid(y,x)) || (exist(y,x+2,false) && !verValid(y,x+2));
            boolean horFail = (exist(y,x-2,true) && !horValid(y,x-2)) || (exist(y,x+2,true) && !horValid(y,x+2));
            // System.out.println(verFail + " " + horFail);
                
            if (verFail || horFail) {
                wall[y][x] = true;
                wall[y][x+1] = true;
            }
        } else {
            if (!exist(y,x,true)) wall[y][x] = false;
            wall[y+1][x] = false;
            
            boolean verFail = (exist(y+2,x,false) && !verValid(y+2,x));
            boolean horFail = (exist(y+2,x-2,true) && !horValid(y+2,x-2)) || (exist(y+2,x,true) && !horValid(y+2,x));
            // System.out.println(verFail + " " + horFail);
            
            if (verFail || horFail) {
                wall[y][x] = true;
                wall[y+1][x] = true;
            }
        }
    }
    public int[][] solution(int N, int[][] build_frame) {
        n = N;
        wall = new boolean[n*2+2][n*2+2];
        ArrayList<int[]> list = new ArrayList<>();
        
        for (int[] bf : build_frame) {
            int x = bf[0]*2;
            int y = bf[1]*2;
            boolean hor = (bf[2] == 1);
            boolean ins = (bf[3] == 1);
            if (ins) insert(y,x,hor);
            else delete(y,x,hor);
        }

        for (int j = 0; j <= n*2; j+=2) {
            for (int i = 0; i <= n*2; i+=2) {
                if (!wall[i][j]) continue;
                if (wall[i+1][j]) list.add(new int[]{j/2,i/2,0});
                if (wall[i][j+1]) list.add(new int[]{j/2,i/2,1});
            }
        }
        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}