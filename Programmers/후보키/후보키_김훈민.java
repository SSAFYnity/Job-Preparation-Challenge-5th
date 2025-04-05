import java.util.*;

class Solution {
    int N, M;
    Set<Integer> resultSet = new HashSet<>();
    Set<String> checkSet = new HashSet<>();
    boolean[] choice;
    String[][] relations;
    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        relations = relation;
        choice = new boolean[M];
        for(int i = 1; i <= M; i++){
            select(-1, 0, i);
        }
        return resultSet.size();
    }

    void select(int prev, int depth, int K){
        if(depth == K){
            if(isUnique()){
                int value = makeValue();
                if(canAdd(value)){
                    resultSet.add(value);
                }
            }
            return;
        }

        for(int i = prev+1; i < M; i++){
            if(!choice[i]){
                choice[i] = true;
                select(i, depth + 1, K);
                choice[i] = false;
            }
        }
    }

    boolean canAdd(int v){
        for(int r : resultSet){
            if(v == (r | v)) return false;
        }
        return true;
    }

    int makeValue(){
        int r = 0;
        for(int i = 0; i < M; i++){
            if(choice[i]){
                r = r | 1 << i;
            }
        }
        return r;
    }

    boolean isUnique(){
        checkSet.clear();
        for(int i = 0; i < N; i++){
            String temp = "";
            for(int j = 0; j < M; j++){
                if(choice[j]){
                    temp += relations[i][j];
                }
            }
            if(checkSet.contains(temp)) return false;
            checkSet.add(temp);
        }
        return true;
    }
}