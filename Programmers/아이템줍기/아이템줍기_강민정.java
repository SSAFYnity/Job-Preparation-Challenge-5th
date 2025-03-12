import java.util.*;

class Solution {
    static char map[][]=new char[101][101];

    public static void draw(int y1, int x1, int y2, int x2) {
        // 경계를 '2'로 표시하고, 내부를 '1'로 표시
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (map[i][j] == '1') continue; // 이미 '1'으로 표시된 곳은 건너뛰기
                map[i][j] = '1';  // 내부를 '1'로 표시
                if (i == y1 || i == y2 || j == x1 || j == x2) {
                    map[i][j] = '2';  // 경계를 '2'로 표시
                }
            }
        }
    }

    public static int bfs(int Y,int X,int findY,int findX){
        int yy[]={-1,1,0,0};
        int xx[]={0,0,-1,1};
        Queue<Integer[]> queue=new LinkedList<>();
        queue.add(new Integer[]{Y,X,0});
        boolean visited[][]=new boolean[101][101];
        while(!queue.isEmpty()){
            Integer temp[]=queue.poll();
            int prevY=temp[0];
            int prevX=temp[1];
            int count=temp[2];
            if(prevY==findY&&prevX==findX)
                return count/2;
            for(int i=0;i<4;i++){
                int nextY=prevY+yy[i];
                int nextX=prevX+xx[i];
                if(nextY<0||nextX<0||nextY>=map.length||nextX>=map[0].length)
                    continue;
                if(visited[nextY][nextX]==true||map[nextY][nextX]!='2')
                    continue;

                visited[nextY][nextX]=true;
                queue.add(new Integer[]{nextY,nextX,count+1});

            }
        }

        return 0;
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int i=0;i<rectangle.length;i++){
            int y1=rectangle[i][1];
            int x1=rectangle[i][0];
            int y2=rectangle[i][3];
            int x2=rectangle[i][2];
            draw(y1*2,x1*2,y2*2,x2*2);
        }

        return bfs(characterY*2,characterX*2,itemY*2,itemX*2);
    }
}