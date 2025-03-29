import java.util.*;

/*
아이디어: 구현
1.사람의 위치 정보(행, 열) list에 저장
2.사람 수만큼 방문 배열 생성
3.list 탐색하기
    - > 2 : 통과
    - <= 2 : 대각선, 행, 열, 구분해서 파티션 존재 확인
        -> 두 좌표가 같은 행일 경우, 기준 좌표의 (행, 열+1) 확인
        -> 두 좌표가 같은 열일 경우, 기준 좌표의 (행+1, 열) 확인
        -> 두 좌표가 대각선 배치, 기준 좌표(행, 열+1), (행+1, 열) 확인
*/
class Solution {
    class Point{
        int x;
        int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0; i < 5; i++){
            System.out.println("단계: "+i);
            if(checkRoom(places[i])) answer[i] = 1; // 판별하러가기
            else answer[i] = 0;
        }
        return answer;
    }
    
    boolean checkRoom(String [] places){
        // 1. room 배열 초기화
        String [][] room = new String[5][5];
        for(int i = 0; i < places.length; i++){
            room[i] = places[i].split("");
        }
        
        // 2. 사람 위치
        List<Point> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(room[i][j].equals("P")){
                    list.add(new Point(i, j));  // 사람 위치 저장
                }
            }
        }
        
        // 3. 맨해튼 거리 판별
        boolean flag;
        for(int i = 0; i < list.size(); i++){
            Point datumPoint = list.get(i);
            for(int j = i+1; j < list.size(); j++){
                flag = true;
                Point tmpPoint = list.get(j);
                int mDis = Math.abs(datumPoint.x-tmpPoint.x) + Math.abs(datumPoint.y - tmpPoint.y); // 맨해튼 거리 구하기
                if(mDis > 2) continue;
                else{
                    if(datumPoint.x == tmpPoint.x){ // 같은 행 
                        if(!room[datumPoint.x][datumPoint.y+1].equals("X"))return false;
                    }else if(datumPoint.y == tmpPoint.y){   // 같은 열
                        if(!room[datumPoint.x+1][datumPoint.y].equals("X"))return false;
                    }
                    else{  // 대각선
                        if(datumPoint.x < tmpPoint.x){
                            if(datumPoint.x+1 >= 5 || datumPoint.y+1 >= 5) continue;
                            if(!room[datumPoint.x][datumPoint.y+1].equals("X") || !room[datumPoint.x+1][datumPoint.y].equals("X")) return false;
                        }else{
                            if(datumPoint.x+1 >= 5 || datumPoint.y+1 >= 5) continue;
                            if(!room[datumPoint.x][datumPoint.y-1].equals("X") || !room[datumPoint.x+1][datumPoint.y].equals("X")) return false;
                        } 
                    }
                }
            }
        }
        return true;
    }
    
}