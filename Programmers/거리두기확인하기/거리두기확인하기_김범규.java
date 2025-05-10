import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        ArrayList<Node> pZone = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < 5; i++){
            //System.out.println(i + "번째");
            boolean check = false;
            char[][] map = new char[5][5];
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    map[j][k] = places[i][j].charAt(k);
                    if(map[j][k] == 'P'){
                        pZone.add(new Node(j, k));
                    }
                }
            }
            
            //각 P를 비교
            out :for(int a = 0; a < pZone.size() - 1; a++){
                int ar = pZone.get(a).r;
                int ac = pZone.get(a).c;
                for(int b = a + 1; b < pZone.size(); b++){
                    int br = pZone.get(b).r;
                    int bc = pZone.get(b).c;
                    //System.out.println("현재 " + ar + " " + ac + " " + br + " " + bc + "보는 중");
                    
                    //맨해튼 거리 2초과시 넘김
                    if((Math.abs(ar - br) + Math.abs(ac - bc)) > 2){
                        continue;
                    }
                    //맨해튼 거리 2미만시 거리두기 X 
                    else if((Math.abs(ar - br) + Math.abs(ac - bc)) < 2){
                        ans.add(0);
                        check = true;
                        break out;
                    }
                    //맨해튼 거리 2일시 조건 3개
                    //1. 위 아래로 마주보는 경우
                    //2. 왼쪽 오른쪽
                    //3. 대각선
                    else {
                        if(Math.abs(ar - br) == 2){
                            if(map[(ar + br) / 2][ac] == 'O'){
                                ans.add(0);
                                check = true;
                                break out;
                            }
                        }
                        else if(Math.abs(ac - bc) == 2){
                            if(map[ar][(ac + bc) / 2] == 'O'){
                                ans.add(0);
                                check = true;
                                break out;
                            }
                        }
                        else{
                            if(map[ar][bc] == 'O' || map[br][ac] == 'O'){
                                ans.add(0);
                                check = true;
                                break out;
                            }
                        }
                    }
                                 
                }
            }  
            //해당사항없으면 거리두기 지킴
            //System.out.println(check + "...");
            if(!check){
                ans.add(1);
            }
            pZone.clear();
        }
        
        int[] answer = new int[5];
        
        for(int i = 0; i < 5; i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
    
    public class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    
}