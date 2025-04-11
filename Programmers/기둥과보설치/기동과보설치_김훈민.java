import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
    static boolean[][] pillar;
    static boolean[][] beam;
    static int N;

    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        pillar = new boolean[n + 1][n + 1];
        beam   = new boolean[n + 1][n + 1];

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];

            if (b == 1) {
                if (a == 0) {
                    pillar[x][y] = true;
                    if (!checkStructure()) {
                        pillar[x][y] = false;
                    }
                } else {
                    beam[x][y] = true;
                    if (!checkStructure()) {
                        beam[x][y] = false;
                    }
                }
            } else {
                if (a == 0) {
                    pillar[x][y] = false;
                    if (!checkStructure()) {
                        pillar[x][y] = true;
                    }
                } else {
                    beam[x][y] = false;
                    if (!checkStructure()) {
                        beam[x][y] = true;
                    }
                }
            }
        }

        List<int[]> result = new ArrayList<>();

        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y]) {
                    result.add(new int[]{x, y, 0});
                }
                if (beam[x][y]) {
                    result.add(new int[]{x, y, 1});
                }
            }
        }

        Collections.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    if (o1[1] == o2[1]) {
                        return Integer.compare(o1[2], o2[2]);
                    }
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int[][] answer = new int[result.size()][3];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private boolean checkStructure() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (pillar[x][y]) {
                    if (!checkPillar(x, y)) {
                        return false;
                    }
                }
                if (beam[x][y]) {
                    if (!checkBeam(x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkPillar(int x, int y) {
        if (y == 0) return true;
        if (pillar[x][y - 1]) return true;
        if (x > 0 && beam[x - 1][y]) return true;
        if (beam[x][y]) return true;

        return false;
    }

    private boolean checkBeam(int x, int y) {
        if (pillar[x][y - 1]) return true;
        if (x + 1 <= N && pillar[x + 1][y - 1]) return true;
        if (x > 0 && x + 1 <= N && beam[x - 1][y] && beam[x + 1][y]) return true;

        return false;
    }
}
