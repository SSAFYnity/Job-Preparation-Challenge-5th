import java.util.*;

class Solution {
    public static int solution(int[] stones, int k) {
        int min = 0, max = 200_000_000;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (canCross(stones, k, mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return max;
    }

    private static boolean canCross(int[] stones, int k, int num) {
        int count = 0;
        for (int stone : stones) {
            if (stone - num < 0) {
                count++;
                if (count >= k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }
        return true;
    }
}
