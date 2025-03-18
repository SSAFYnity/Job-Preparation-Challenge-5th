import java.io.*;
import java.util.*;
class Solution {
    static boolean[][] isDeleted; 
    static char[][] box;
    static int[][] way = {{0,0}, {0,1}, {1,0}, {1,1}};
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        box = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                box[i][j] = board[i].charAt(j);
            }
        }        
        while(true) {
            isDeleted = new boolean[m][n];
            List<int[]> curDel = new ArrayList<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(!(i + 1 < m && j + 1 < n))continue; // out of range
                    char item = box[i][j];
                    if(item == 'X')continue;
                    boolean isAnswer = true;
                    for(int w = 0; w < 4; w++){
                        int x = i + way[w][0];
                        int y = j + way[w][1];
                        if(box[x][y] != item){
                            isAnswer = false;
                            break;
                        }
                    }
                    if(isAnswer){
                        for(int w = 0; w < 4; w++){
                            int x = i + way[w][0];
                            int y = j + way[w][1];
                            if(!isDeleted[x][y]){
                                isDeleted[x][y] = true;     
                                curDel.add(new int[]{x, y});
                                answer += 1;
                            }
                        }
                    }
                }
            }
            if(curDel.size() == 0)break;
            for(int[] now : curDel) {
                box[now[0]][now[1]] = 'X';
            }
            
            for (int j = 0; j < n; j++) { // 열 증가 
                List<Character> survived = new ArrayList<>();
                for(int i = m-1; i >= 0; i--) {
                    if(box[i][j] != 'X') {
                        survived.add(box[i][j]);
                    }
                }
                
                int x = m-1;
                int cnt = 0;
                while(cnt < survived.size()){
                    box[x--][j] = survived.get(cnt++);
                }
                while(x >= 0) {
                    box[x--][j] = 'X';
                }
            } 
        }
        
        return answer;
    }
}