import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[][] relation) {
        int answer = 0;
        int N = relation.length;
        int M = relation[0].length;
        List<Integer> keys = new ArrayList<>();
        for(int c = 1; c < (1 << M); c++){
            Set<String> set = new HashSet<>();
            for(int i = 0; i < N; i++) {
                String key = "";
                for(int j = 0; j < M; j++){
                    if((c & (1 << j)) > 0){
                        key+= relation[i][j];
                    }
                }
                set.add(key);
            }
            if(set.size() == N && check(keys, c)){
                keys.add(c);
            }
        }
        return keys.size();
    }
    
    static boolean check(List<Integer> keys, int newKey) {
        for(int key : keys){
            if((key & newKey) == key){
                return false;
            }
        }
        return true;
    }
}
// 유일성, 최소성 