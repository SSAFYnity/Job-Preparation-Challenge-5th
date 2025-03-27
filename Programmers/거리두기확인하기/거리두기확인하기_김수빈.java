import java.util.*;

public class Solution {
    static int[][] dist1 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 상하좌우
    static int[][] dist2 = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};  // 투상하좌우
    static int[][] dist3 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};  // 대각선
    private static boolean checkSocialDistance(char[][] waitingRoom) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (waitingRoom[i][j] == 'P') {
                    boolean[] p = new boolean[4];
                    for (int d1 = 0; d1 < 4; d1++) {
                        int ni = i + dist1[d1][0], nj = j + dist1[d1][1];
                        // 상하좌우 범위 안이면
                        if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5) {
                            // 참가자 존재시 탈락
                            if (waitingRoom[ni][nj] == 'P') {
                                return false;
                            } else if (waitingRoom[ni][nj] == 'X') {
                                p[d1] = true;
                            }
                        }
                    }
                    for (int d2 = 0; d2 < 4; d2++) {
                        // 사이에 파티선 있으므로 맨해튼 거리 안이라도 상관 없음
                        if (p[d2]) {
                            continue;
                        }
                        int ni = i + dist2[d2][0], nj = j + dist2[d2][1];
                        // 투상하좌우 범위 안이면 맨해튼 바로 실격
                        if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && waitingRoom[ni][nj] == 'P') {
                            return false;
                        }
                    }
                    // 상하좌우 0123
                    boolean[] dp = {p[0] && p[2], p[0] && p[3], p[1] && p[3], p[1] && p[2]};
                    for (int d3 = 0; d3 < 4; d3++) {
                        // 사이에 파티선 있으므로 맨해튼 거리 안이라도 상관 없음
                        if (dp[d3]) {
                            continue;
                        }
                        int ni = i + dist3[d3][0], nj = j + dist3[d3][1];
                        // 대각선 범위 안이면 맨해튼 바로 실격
                        if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && waitingRoom[ni][nj] == 'P') {
                            return false;
                        }
                    }
                }
            }
        }
        // 다 통과하면 사람없다는 것이므로 넘김
        return true;
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            String[] place = places[i];
            char[][] room = new char[5][5];
            for (int r = 0; r < 5; r++) {
                String line = place[r];
                for (int c = 0; c < 5; c++) {
                    char seat = line.charAt(c);
                    room[r][c] = seat;
                }
            }
            if (checkSocialDistance(room)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }


        return answer;
    }
}
