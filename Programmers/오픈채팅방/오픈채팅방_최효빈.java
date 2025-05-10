import java.util.*;

class Solution {
    
    class Info {
        
        String uid;
        boolean isEntering;
        
        public Info(String uid, boolean isEntering){
            this.uid = uid;
            this.isEntering = isEntering;
        }
    }
    
    public String[] solution(String[] record) {
        Map<String, String> nicknameMap = new HashMap<>();
        Queue<Info> que = new ArrayDeque<>();
        
        for(String rec : record){
            String[] tokens = rec.split(" ");
            String cmd = tokens[0];
            String uid = tokens[1];
            
            if(cmd.equals("Leave")){
                que.offer(new Info(uid, false));
                continue;
            }
                
            String nickname = tokens[2];
            nicknameMap.put(uid, nickname);

            if(cmd.equals("Enter")){
                que.offer(new Info(uid, true));
            }
        }
        
        
        String[] answer = new String[que.size()];
        
        int idx = -1;
        while(!que.isEmpty()){
            Info info = que.poll();
            String nickname = nicknameMap.get(info.uid);
            String enteranceMsg = info.isEntering ? "님이 들어왔습니다." : "님이 나갔습니다.";
            answer[++idx] = nickname + enteranceMsg;
        }
        
        return answer;
    }
}
