class Solution {
    static int N;
    static int M;
    private static int[][] rotateKey(int[][] key) {
        int[][] arr = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = key[M - j - 1][i];
            }
        }
        return arr;
    }

    private static boolean isMatch(int[][] key, int[][] lock) {
        int len = N + 2 * (M - 1);
        for (int x = 0; x <= len - M; x++) {
            for (int y = 0; y <= len - M; y++) {
                int[][] extLock = makeExtendedLock(lock);
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < M; j++) {
                        extLock[x + i][y + j] += key[i][j];
                    }
                }

                boolean match = true;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (extLock[i + M - 1][j + M - 1] != 1) {
                            match = false;
                            break;
                        }
                    }
                    if (!match) break;
                }
                if (match) return true;
            }
        }
        return false;
    }

    private static int[][] makeExtendedLock (int[][] lock) {
        int[][] extLock = new int[N + 2 * (M - 1)][N + 2 * (M - 1)];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                extLock[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        return extLock;
    }

    public boolean solution(int[][] key, int[][] lock) {
        // 0이 홈, 1이 돌기 -> lock의 0과 1이 만나야 하고 1이 두개가 되면 안되고, 모든 0을 없애야함
        M = key.length;
        N = lock.length;


        for (int i = 0; i < 4; i++) {
            if (isMatch(key, lock)) return true;
            key = rotateKey(key);
        }

        return false;
    }
}
