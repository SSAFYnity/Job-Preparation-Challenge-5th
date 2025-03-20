import java.util.*;

class Solution {
    static HashMap<String, String> user;
    public String[] solution(String[] record) {
        user = new HashMap<String, String>();
        // 유저아이디는 유일함
        // 닉네임은 중복가능
        // 순서를 명령어+uid로 기억해야됨
        ArrayList<String> msg = new ArrayList();
        
        for(String in: record){
            StringTokenizer st = new StringTokenizer(in, " ");
            String com = st.nextToken();
            String uid = st.nextToken();
            if(!com.equals("Leave")){
                String name = st.nextToken();
                user.put(uid, name);
            }
            if(!com.equals("Change")){
                msg.add(com+" "+uid);
            }
            // 가장 나중 닉네임이 저장될 것
        }
        ArrayList<String> res = new ArrayList();

        for(String m: msg){
            StringTokenizer st = new StringTokenizer(m, " ");
            String com = st.nextToken();
            String uid = st.nextToken();
            StringBuilder sb = new StringBuilder();
            sb.append(user.get(uid)).append("님이 ");
            if(com.equals("Enter")){
                sb.append("들어왔습니다.");
            }
            if(com.equals("Leave")){
                sb.append("나갔습니다.");
            }
            res.add(sb.toString());
        }
        String[] answer = new String[res.size()];

        int idx= 0;
        for(String s: res){
            answer[idx++] = s;
        }
        return answer;
    }
}