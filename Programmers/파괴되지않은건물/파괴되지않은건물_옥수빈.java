import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill){
        
        int rLen = board.length;
        int cLen = board[0].length;
        int[][] sumMap = new int[rLen][cLen];
        
        for(int i = 0; i < skill.length; i++){
            
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            int sum;
            
            if(type == 1)
                sum = -degree;
            else
                sum = degree;
            
            sumMap[r1][c1] += sum;
            
            if(r2+1 < rLen){
                sumMap[r2+1][c1] -= sum;
                if(c2+1 < cLen)
                    sumMap[r2+1][c2+1] += sum;
            } 
            if(c2+1 < cLen)
                sumMap[r1][c2+1] -= sum;
        }
        
        for(int x = 0; x < cLen; x++){
            int total = 0;
            for(int y = 0; y < rLen; y++){
                total += sumMap[y][x];
                sumMap[y][x] = total;
            }
        }
        
        int answer = 0;
        for(int y = 0; y < rLen; y++){
            int total = 0;
            for(int x = 0; x < cLen; x++){
                total += sumMap[y][x];
                sumMap[y][x] = total;
                if(total + board[y][x] > 0)
                    answer++;
            }
        }
        
        return answer;
    }
}