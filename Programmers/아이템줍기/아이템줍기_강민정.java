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