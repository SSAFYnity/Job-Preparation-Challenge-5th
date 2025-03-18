/*
아이디어: 구현
1. 완탐으로 같은모양-2*2 찾고 표시
2. 표시된 곳 pop
3. pop한 다음에 위에서 내려서 빈 곳 채우기
4. answer가 증가하지 않을 때까지 반복
*/
import java.util.*;

class Solution {
    String [][] map;
    boolean [][] visited;
    int m, n;
    int answer;         // 총 터지는 블록 수
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        // 2차원 배열로 만들기
        map = new String[m][n]; // m:행, n: 열
        for(int i = 0; i < m; i++){
            map[i] = board[i].split("");
        }
        
        int curAnswer = 0;
        
        // 나올때까지 반복
        while(true){
            curAnswer = answer;
            visited = new boolean[m][n];
            
            // 1. 블록 찾기
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(!map[i][j].equals("")){
                        popBlock(i, j, visited);
                    }
                }
            }
            // 2. 블록 pop
            int cnt = 0;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(visited[i][j]){
                        map[i][j] = "";
                        cnt++;
                    }
                }
            }
            answer += cnt;
            if(answer == curAnswer) break;  // pop한 게 없을 때
            
            // 2. 재정비
            for(int i = 0; i < n; i++){
                updateMap(i);
            }
        }
        return answer;
    }
    
    void popBlock(int x, int y, boolean [][] visited){
        if(x+2 > m || y+2 > n) return;
        String target = map[x][y];  // 기준 캐릭터
        
        for(int i = x; i < x+2; i++){
            for(int j = y; j < y+2; j++){
                if(map[i][j].equals("") || !map[i][j].equals(target)) return;
            }
        }
        
        for(int i = x; i < x+2; i++){
            for(int j = y; j < y+2; j++){
                if(!map[i][j].equals("") && map[i][j].equals(target)){
                    visited[i][j] = true;
                }
            }
        }
    }
    
    void updateMap(int col){
        for(int i = m-1; i > 0; i--){
            if(map[i][col].equals("")){
                for(int row = i-1; row >= 0; row--){
                    if(!map[row][col].equals("")){
                        map[i][col] = map[row][col];
                        map[row][col] = "";
                        break;
                    }
                }
            }
        }
    }
}