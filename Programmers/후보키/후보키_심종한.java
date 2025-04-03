import java.util.*;

class Solution {
    
    int N, M;
    List<Integer> list = new ArrayList<>();
    String[][] relation;
    
    public int solution(String[][] relation) {
        this.relation = relation;
        this.N = relation.length;
        this.M = relation[0].length;
        
        for (int i = 1; i < (1 << M); i++) {
            if (isMinimal(i) && isUnique(i)) {
                list.add(i);
            }
        }
        return list.size();
    }
    
    private boolean isMinimal(int bit) {
        for (int key : list) {
            if ((key & bit) == key) {
                return false;
            }
        }    
        return true; 
    }
    
    private boolean isUnique(int bit) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j++) {
                if ((bit & (1 << j)) != 0) {
                    sb.append(relation[i][j]);
                }
            }
            set.add(sb.toString());
        }
        return set.size() == N;
    }    
}
