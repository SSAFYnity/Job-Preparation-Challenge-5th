import java.util.*;

class Solution {
    int N, M;
    char[][] graph;
    boolean[][] visited;
    Queue<Point> queue = new LinkedList<>();
    int[][] vector = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        graph = new char[N+2][M+2];
        visited = new boolean[N+2][M+2];
        for(int i = 0; i < N+2; i++){
            Arrays.fill(graph[i], '.');
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                graph[i+1][j+1] = storage[i].charAt(j);
            }
        }
        for(String r : requests){
            if(r.length() == 1) useCrane(r.charAt(0));
            else useForkLift(r.charAt(0));
        }
        return countResult();
    }

    int countResult(){
        int temp = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(graph[i+1][j+1] != '.')
                    temp++;
            }
        }
        return temp;
    }

    void useCrane(char a){
        setVisited();
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            for(int[] v : vector){
                int nx = p.x + v[0];
                int ny = p.y + v[1];
                if(!isIn(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(graph[nx][ny] == a){
                    graph[nx][ny] = '.';
                    visited[nx][ny] = true;
                    continue;
                }
                if(graph[nx][ny] == '.'){
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    void useForkLift(char a){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(graph[i+1][j+1] == a)
                    graph[i+1][j+1] = '.';
            }
        }
    }

    boolean isIn(int x, int y){
        return 0 <= x && x < N+2 && 0 <= y && y < M+2;
    }

    void setVisited(){
        for(int i = 0; i < N+2; i++){
            Arrays.fill(visited[i], false);
        }
    }

    class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}