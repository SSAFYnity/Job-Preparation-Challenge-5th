import java.util.*;

class Solution {
    public boolean canPass(int[] stones, int k, int p) {
        int cnt = 0;
        for (int i = 0; i <= stones.length-k; i++) {
            if (stones[i] >= p) continue;
            cnt = 0;
            int j = i;
            while (stones[j] < p) {
                cnt++;
                if (cnt >= k) return false;
                if (++j >= stones.length) break;
            }
            i = j;
        }
        return true;
    }
    public int bin(int[] stones, int k) {
        int s = Integer.MAX_VALUE;
        int e = 1;
        for (int i : stones) {
            s = Math.min(s, i);
            e = Math.max(e, i);
        }
        int maxi = e;
        int p = (s + e) / 2;
        while (s <= e && e <= maxi) {
            p = (s + e) / 2;
            if (canPass(stones, k, p)) s = p + 1;
            else e = p - 1;
        }
        return e;
    }
    public int solution(int[] stones, int k) {
        
        return bin(stones, k);
    }
}