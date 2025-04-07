import java.util.*;

class Solution {
    int[] lock;
    int[][] keys;
    int n, m, mask;
    public int[] rotate(int[][] key, int d) {
        int[] arr = new int[m];
        int temp = 0;
        if (d == 0) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i] = (arr[i] << 1) + key[i][j];
                }
            }
        } else if (d == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i] = (arr[i] << 1) + key[m-1-j][i];
                }
            }
        } else if (d == 2) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i] = (arr[i] << 1) + key[m-1-i][m-1-j];
                }
            }
        } else {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i] = (arr[i] << 1) + key[j][m-1-i];
                }
            }
        }
        
        return arr;
    }

    public int shift(int num, int offset) {
        if (offset < 0) return num >> Math.abs(offset);
        else return num << offset;
    }
    public boolean checkDetail(int d, int oy, int ox) {
        print(oy + " " + ox);
        for (int i = 0; i < n; i++) {
            int y = i + oy;
            int now = 0;
            if (!(y < 0 || y >= m)) now = shift(keys[d][y], ox) & mask;
            print(i + " " + binStr(lock[i]) + " " + y + " " + binStr(now));
            if (lock[i] + now != mask) return false;
        }
        return true;
    }
    public boolean check(int d) {
        // 0 -> m-1 ~ n-1 -> 0
        print(d+"방향");
        for (int i = -(n-1); i < m; i++) {
            for (int j = -(n-1); j < m; j++) {
                if (checkDetail(d, i, j)) return true;
            }
        }
        return false;
    }
    public void setting(int[][] key, int[][] lockO) {
        keys = new int[4][m];
        for (int i = 0; i < 4; i++) {keys[i] = rotate(key, i);}
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) lock[i] = (lock[i] << 1) + lockO[i][j];
        }
    }
    public void print(String str) {
        // System.out.println(str);
    }
    public String binStr(int num) {return Integer.toBinaryString(num);}
    public boolean solution(int[][] key, int[][] lockO) {
        boolean answer = false;
        n = lockO.length;
        m = key.length;
        mask = (int) Math.pow(2, n) - 1;
        lock = new int[n];
        setting(key, lockO);
        for (int i = 0; i < m; i++) print(binStr(keys[3][i]));
        for (int i = 0; i < 4 && !answer; i++) {answer = check(i);}
        
        return answer;
    }
}