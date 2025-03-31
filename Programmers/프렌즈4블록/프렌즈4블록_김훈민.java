import java.util.*;

class Solution {
    boolean[][] brokenBlock;
    char[][] boards;
    int M, N;
    public int solution(int m, int n, String[] board) {
        M = m;
        N = n;
        brokenBlock = new boolean[m][n];
        boards = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                boards[i][j] = board[i].charAt(j);
            }
        }
        while(findBrokenBlock()){
            breakBlocks();
            resetBrokenBlock();
            downBlocks();
        }
        int answer = countRemainBlocks();
        return answer;
    }

    int countRemainBlocks(){
        int cnt = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(boards[i][j] == '.')
                    cnt++;
            }
        }
        return cnt;
    }

    void downBlocks(){
        Queue<Character> queue = new LinkedList<>();
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                char cur = boards[M-j-1][i];
                if(cur == '.')
                    continue;
                queue.add(cur);
            }
            for(int j = 0; j < M; j++){
                if(!queue.isEmpty())
                    boards[M-j-1][i] = queue.poll();
                else
                    boards[M-j-1][i] = '.';
            }
        }
    }

    void breakBlocks(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(brokenBlock[i][j])
                    boards[i][j] = '.';
            }
        }
    }

    void resetBrokenBlock(){
        for(int i = 0; i < M; i++){
            Arrays.fill(brokenBlock[i], false);
        }
    }

    boolean findBrokenBlock(){
        boolean flag = false;
        for(int i = 0; i < M-1; i++){
            for(int j = 0; j < N-1; j++){
                if(boards[i][j] == '.')
                    continue;
                if(check4Block(i, j))
                    flag = true;
            }
        }
        return flag;
    }

    boolean check4Block(int x, int y){
        char origin = boards[x][y];
        int count = 0;
        for(int i = x; i < x + 2; i++){
            for(int j = y; j < y + 2; j++){
                if(boards[i][j] == origin)
                    count++;
            }
        }
        if(count == 4){
            for(int i = x; i < x + 2; i++){
                for(int j = y; j < y + 2; j++){
                    brokenBlock[i][j] = true;
                }
            }
            return true;
        }
        return false;
    }
}