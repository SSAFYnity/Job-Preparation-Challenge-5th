import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int row = relation.length;
        int col = relation[0].length;
        List<Integer> candidateKeys = new ArrayList<>();

        for (int comb = 1; comb < (1 << col); comb++) {
            Set<String> set = new HashSet<>();

            for (int i = 0; i < row; i++) {
                StringBuilder sb = new StringBuilder();
                // 비트마스크 밀면서 검사
                for (int j = 0; j < col; j++) {
                    if ((comb & (1 << j)) != 0) {
                        sb.append(relation[i][j]).append(",");
                    }
                }
                set.add(sb.toString());
            }

            if (set.size() == row) {
                boolean isMinimal = true;
                for (int key : candidateKeys) {
                    if ((key & comb) == key) {
                        isMinimal = false;
                        break;
                    }
                }

                if (isMinimal) {
                    candidateKeys.add(comb);
                }
            }
        }

        return candidateKeys.size();
    }
}
