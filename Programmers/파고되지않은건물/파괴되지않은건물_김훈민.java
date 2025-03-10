import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] diff = new int[board.length+1][board[0].length+1];
        for(int[] skil : skill){
            if(skil[0] == 1){
                dmgCheck(diff, skil[1], skil[2], skil[3], skil[4], skil[5]);
            } else dmgCheck(diff, skil[1], skil[2], skil[3], skil[4], -skil[5]);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 1; j < board[0].length; j++){
                diff[i][j] += diff[i][j-1];
            }
        }
        for(int j = 0; j < board[0].length; j++){
            for(int i = 1; i < board.length; i++){
                diff[i][j] += diff[i-1][j];
            }
        }
        int answer = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] += diff[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        return answer;
    }

    static void dmgCheck(int[][] board, int x1, int y1, int x2, int y2, int dmg){
        board[x2+1][y2+1] -= dmg;
        board[x1][y1] -= dmg;
        board[x2+1][y1] += dmg;
        board[x1][y2+1] += dmg;
    }
}