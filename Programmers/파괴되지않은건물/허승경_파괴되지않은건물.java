import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int [][] diff = new int[board.length + 1][board[0].length + 1];
        
        // diff에 degree 기록
        for(int i = 0; i < skill.length; i++){
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(skill[i][0] == 1) degree = -degree;  // 공격 -> 음수
            
            diff[r1][c1] += degree;
            diff[r2+1][c1] -= degree;
            diff[r1][c2+1] -= degree;
            diff[r2+1][c2+1] += degree;
        }
        
        // 누적합 -> 열방향 (오 -> 왼)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <  board[0].length; j++) {
                diff[i + 1][j] += diff[i][j];
            }
        }
        
        // 누적합 -> 행방향 (왼 -> 오)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <  board[0].length; j++) {
                diff[i][j + 1] += diff[i][j];
            }
        }

        
        // 확인
        int answer = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] += diff[i][j];
                if(board[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
}