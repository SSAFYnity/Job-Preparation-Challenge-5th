import java.util.*;

class Solution {
    int keySize, lockSize;
    int lockHole;

    public boolean solution(int[][] key, int[][] lock) {
        lockSize = lock.length;
        keySize = key.length;
        lockHole = 0;

        // 자물쇠 홈 개수 세기
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (lock[i][j] == 0) {
                    lockHole++;
                }
            }
        }

        // 자물쇠 확장
        int exp = lockSize + 2 * (keySize - 1);
        int[][] board = new int[exp][exp];
        for (int y = 0; y < exp; y++) {
            Arrays.fill(board[y], -1); // 기본값 -1
        }

        // 자물쇠 복사
        for (int y = 0; y < lockSize; y++) {
            for (int x = 0; x < lockSize; x++) {
                board[y + keySize - 1][x + keySize - 1] = lock[y][x];
            }
        }


        // 90도씩 회전하면서 검사
        for (int d = 0; d < 4; d++) {
            rotateKey(key);
            if (moveKey(key, board)) return true;
        }

        return false;
    }

    public void rotateKey(int[][] key) {
        int[][] tmp = new int[keySize][keySize];
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                tmp[j][keySize - i - 1] = key[i][j];
            }
        }
        for (int i = 0; i < keySize; i++) {
            System.arraycopy(tmp[i], 0, key[i], 0, keySize);
        }
    }

    public boolean moveKey(int[][] key, int[][] board) {
        int limit = board.length - keySize + 1;
        for (int sy = 0; sy < limit; sy++) {
            for (int sx = 0; sx < limit; sx++) {
                if (checkLock(sx, sy, key, board)) return true;
            }
        }
        return false;
    }

    public boolean checkLock(int sx, int sy, int[][] key, int[][] board) {
        int count = 0;

        for (int ky = 0; ky < keySize; ky++) {
            for (int kx = 0; kx < keySize; kx++) {
                int by = sy + ky;
                int bx = sx + kx;

                // board 범위 확인
                if (by >= board.length || bx >= board.length) continue;
                if (board[by][bx] == -1) continue;

                if (board[by][bx] == 0 && key[ky][kx] == 1) {
                    count++;
                } else if (board[by][bx] == 1 && key[ky][kx] == 1) {
                    return false;
                }
            }
        }

        return count == lockHole;
    }

    public void print(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("------------------------");
    }
}
