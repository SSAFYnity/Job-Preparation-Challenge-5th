import java.util.*;
/*
아이디어 : Queue, HashMap
1. enter, leave 할 때마다 que에 저장하기 -> String 배열
1-1. enter할 때마다 유저아이디 등록하기 -> 
2. chage일 경우 HashMap에서 이름 변경
3. que에서 꺼내서 result 출력
*/
class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> hmap = new HashMap<>();
        Queue<String []> que = new LinkedList<>();
        
        for(String str : record){
            String[] tmp = str.split(" ");
            if(tmp[0].equals("Enter")){     // 들어오기
                if(!hmap.containsKey(tmp[1])){
                    hmap.put(tmp[1], tmp[2]);
                }else{
                    if(!hmap.get(tmp[1]).equals(tmp[2])){
                        hmap.replace(tmp[1], tmp[2]);
                    }
                }
                que.add(new String[] {tmp[1], tmp[0]});
            }else if(tmp[0].equals("Change")){      // 이름 변경
                hmap.replace(tmp[1], tmp[2]);
            }else{
                que.add(new String[] {tmp[1], tmp[0]});
            }
        }
        
        String[] answer = new String [que.size()]; 
        String enterStr = "님이 들어왔습니다.";
        String leaveStr = "님이 나갔습니다.";
        int idx = 0;
        while(!que.isEmpty()){
            String [] res = que.poll();
            if(res[1].equals("Enter")){
                String str = hmap.get(res[0]) + enterStr;
                answer[idx] = str;
            }else{
                String str = hmap.get(res[0]) + leaveStr;
                answer[idx] = str;
            }
            idx++;
        }
        

        return answer;
    }
}