import java.util.*;

class Solution {
    int [][] map;
    public int solution(int[][] board, int[][] skill) {
        map = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            map[i] = Arrays.copyOf(board[i], board[i].length);
        }
        
        // 공격 + 회복
        for(int i = 0; i < skill.length; i++){
            if(skill[i][0] == 1){       // 적의 공격
                decreaseDegree(skill[i][1], skill[i][2], skill[i][3], skill[i][4],
                              skill[i][5]);
            }else{      // 아군의 회복
                increaseDegree(skill[i][1], skill[i][2], skill[i][3], skill[i][4],
                              skill[i][5]);
            }
        }
        
        // 확인
        int answer = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
    
    void decreaseDegree(int r1, int c1, int r2, int c2, int degree){
        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                map[i][j] -= degree;
            }
        }
    }
    
    void increaseDegree(int r1, int c1, int r2, int c2, int degree){
        for(int i = r1; i <= r2; i++){
            for(int j = c1; j <= c2; j++){
                map[i][j] += degree;
            }
        }
    }
    
    
}