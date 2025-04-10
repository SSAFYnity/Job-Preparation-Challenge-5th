class Solution {
    int N, M;
    int K;
    int[][] realGraph;
    int[][] graph;
    int[][] keys;
    int[][] locks;
    int[] pointer = {0, 0};
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        keys = key;
        locks = lock;
        K = N+2*M-2;
        graph = new int[K][K];
        realGraph = new int[K][K];
        setting();
        do{
            if(trySolve()){
                if(isCorrect()) return true;
            }
            resetGraph();
            for(int i = 0; i < 3; i++){
                turnRight();
                if(trySolve()){
                    if(isCorrect()) return true;
                }
                resetGraph();
            }
            turnRight();
        }
        while(goOne());
        return false;
    }

    boolean trySolve(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                int x = i + pointer[0];
                int y = j + pointer[1];
                if(keys[i][j] == 1 && graph[x][y] == 1) return false;
                if(keys[i][j] == 1 && graph[x][y] == 0) graph[x][y] = 1;
            }
        }
        return true;
    }

    void turnRight(){
        for(int i = 0; i < M; i++){
            for(int j = i; j < M; j++){
                int temp = keys[i][j];
                keys[i][j] = keys[j][i];
                keys[j][i] = temp;
            }
        }

        for(int i = 0; i < M; i++){
            reverseRow(keys[i]);
        }
    }

    void reverseRow(int[] row){
        int start = 0;
        int end = M-1;
        while(start < end){
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }

    boolean goOne(){
        pointer[1]++;
        if(pointer[1] >= N+M-1){
            pointer[1] = 0;
            pointer[0]++;
        }
        if(pointer[0] >= N+M-1){
            return false;
        }
        return true;
    }

    boolean isCorrect(){
        for(int i = M-1; i < N+M-1; i++){
            for(int j = M-1; j < N+M-1; j++){
                if(graph[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    void resetGraph(){
        for(int i = 0; i < K; i++){
            for(int j = 0; j < K; j++){
                graph[i][j] = realGraph[i][j];
            }
        }
    }

    void setting(){
        for(int i = M-1; i < N+M-1; i++){
            for(int j = M-1; j < N+M-1; j++){
                graph[i][j] = locks[i-(M-1)][j-(M-1)];
                realGraph[i][j] = locks[i-(M-1)][j-(M-1)];
            }
        }
    }

    void showKeys(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                sb.append(keys[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}