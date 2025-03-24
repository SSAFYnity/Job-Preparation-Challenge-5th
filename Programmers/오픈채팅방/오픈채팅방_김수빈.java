import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        List<String[]> lst = new ArrayList<>();
        for (String r : record) {
            String[] info = r.split(" ");
            String word = info[0];
            String id = info[1];
            // 만약 들어온 사람
            if (word.equals("Enter")) {
                String nick = info[2];
                lst.add(new String[]{id, word});  // 순서 기록
                map.put(id, nick);  // 닉네임 기록
            } else if (word.equals("Leave")) {
                lst.add(new String[]{id, word});  // 순서 기록
            } else if (word.equals("Change")) {
                String nick = info[2];
                map.put(id, nick);  // 닉네임 변경
            }
        }
        String[] answer = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            String[] line = lst.get(i);
            String nick = map.get(line[0]);
            if (line[1].equals("Enter")) {
                answer[i] = nick + "님이 들어왔습니다.";
            } else {
                answer[i] = nick + "님이 나갔습니다.";
            }
        }

        return answer;
    }
}
