import java.util.*;

class Solution {
    // 90도 회전 함수
    public int[][] rotate(int[][] key, int keySize) {
        int[][] rotated = new int[keySize][keySize];
        for (int x = 0; x < keySize; x++) {
            for (int y = 0; y < keySize; y++) {
                rotated[x][y] = key[keySize - 1 - y][x];
            }
        }
        return rotated;
    }

    // 깊은 복사를 위한 함수
    private int[][] deepCopy(int[][] arr) {
        int size = arr.length;
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int keySize = key.length;
        int lockSize = lock.length;

        // 자물쇠에서 홈 부분(0) 갯수 세기
        int lockHomeCnt = 0;
        for (int x = 0; x < lockSize; x++) {
            for (int y = 0; y < lockSize; y++) {
                if (lock[x][y] == 0) {
                    lockHomeCnt++;
                }
            }
        }

        // 열쇠 회전 및 자물쇠 맞추기
        for (int i = 0; i < 4; i++) {  // 0도, 90도, 180도, 270도 회전
            if (tryLock(key, lock, keySize, lockSize)) {
                return true;
            }
            key = rotate(key, keySize);  // 키를 90도 회전시킴
        }

        return false;
    }

    // 자물쇠에 열쇠를 맞출 수 있는지 검사
    private boolean tryLock(int[][] key, int[][] lock, int keySize, int lockSize) {
        for (int lockX = 0; lockX < lockSize; lockX++) {  // 자물쇠 행
            for (int lockY = 0; lockY < lockSize; lockY++) {  // 자물쇠 열
                // 열쇠를 자물쇠에 놓기 위해서 시도
                for (int startX = -keySize + 1; startX < lockSize; startX++) {  // 키의 시작 행
                    for (int startY = -keySize + 1; startY < lockSize; startY++) {  // 키의 시작 열
                        if (canFit(key, lock, keySize, lockSize, startX, startY, lockX, lockY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // 키가 자물쇠에 맞을 수 있는지 확인하는 함수
    private boolean canFit(int[][] key, int[][] lock, int keySize, int lockSize, int startX, int startY, int lockX, int lockY) {
        int cnt = 0;

        // 자물쇠에 키가 맞는지 확인
        for (int x = 0; x < keySize; x++) {
            for (int y = 0; y < keySize; y++) {
                int lockPosX = lockX + x + startX;
                int lockPosY = lockY + y + startY;

                // 키가 자물쇠 안에 들어가는지 확인
                if (lockPosX >= 0 && lockPosX < lockSize && lockPosY >= 0 && lockPosY < lockSize) {
                    if (key[x][y] == 1 && lock[lockPosX][lockPosY] == 1) {
                        return false;  // 키의 돌기와 자물쇠의 돌기가 겹침
                    }
                    if (key[x][y] == 1 && lock[lockPosX][lockPosY] == 0) {
                        cnt++;  // 홈을 채움
                    }
                }
            }
        }

        // 자물쇠의 모든 홈을 채웠다면
        return cnt == countLockHoles(lock, lockSize);
    }

    // 자물쇠의 홈(0)의 개수를 세는 함수
    private int countLockHoles(int[][] lock, int lockSize) {
        int cnt = 0;
        for (int x = 0; x < lockSize; x++) {
            for (int y = 0; y < lockSize; y++) {
                if (lock[x][y] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
