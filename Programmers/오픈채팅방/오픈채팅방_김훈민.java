import java.util.*;

class Solution {
    HashMap<String, String> map = new HashMap<>();
    ArrayList<EnterRecord> enterList = new ArrayList<>();
    public String[] solution(String[] record) {
        StringTokenizer st;
        for(String r : record){
            st = new StringTokenizer(r);
            String order = st.nextToken();
            if(order.equals("Enter")){
                enter(st.nextToken(), st.nextToken());
            } else if(order.equals("Leave")){
                leave(st.nextToken());
            } else {
                change(st.nextToken(), st.nextToken());
            }
        }
        String[] answer = new String[enterList.size()];
        int count = 0;
        for(EnterRecord r : enterList){
            if(r.in){
                answer[count++] = map.get(r.id) + "님이 들어왔습니다.";
            } else {
                answer[count++] = map.get(r.id) + "님이 나갔습니다.";
            }
        }
        return answer;
    }

    void enter(String id, String nickName){
        map.put(id, nickName);
        enterList.add(new EnterRecord(true, id));
    }

    void leave(String id){
        enterList.add(new EnterRecord(false, id));
    }

    void change(String id, String nickName){
        map.put(id, nickName);
    }

    // true
    class EnterRecord{
        boolean in;
        String id;

        EnterRecord(boolean in, String id){
            this.in = in;
            this.id = id;
        }
    }
}