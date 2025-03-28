import java.util.*;

class Node{
    int i, j, d;

    Node(int i, int j, int d){
        this.i=i;
        this.j=j;
        this.d=d;
    }
}

class Solution {

    public boolean valid(int nx, int ny){
        if(-1 < nx && nx < 5 && -1 < ny && ny < 5){
            return true;
        }
        return false;
    }

    public boolean bfs(int x, int y, String[] place){
        Queue<Node> que=new LinkedList<>();
        que.add(new Node(x, y, 0));
        boolean[][] visited=new boolean[5][5];
        visited[x][y]=true;
        int[][] pos={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!que.isEmpty()){
            Node node=que.poll();
            for(int[] dxdy : pos){
                int nx=dxdy[0]+node.i;
                int ny=dxdy[1]+node.j;
                if(!valid(nx, ny)) continue;
                if(!visited[nx][ny]){
                    visited[nx][ny]=true;
                    //node.d++;
                    if(place[nx].substring(ny, ny+1).equals("P") && node.d+1 <= 2){
                        return false;
                    }
                    if(!place[nx].substring(ny, ny+1).equals("X")){
                        que.add(new Node(nx, ny, node.d+1));
                    }
                }
            }

        }
        return true;
    }

    public int[] solution(String[][] places) {
        ArrayList<Integer> answer = new ArrayList<>();
        for(String[] place : places){
            int flag=1;
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(place[i].substring(j, j+1).equals("P")){
                        if(!bfs(i, j, place)) {
                            flag = 0;
                            break;
                        }
                    }
                }
                if(flag != 1) break;
            }
            answer.add(flag);

        }

        return answer.stream().mapToInt(i->i).toArray();
    }
}