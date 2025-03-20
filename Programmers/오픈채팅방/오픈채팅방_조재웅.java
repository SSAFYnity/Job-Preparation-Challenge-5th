import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> userMap = new HashMap<>();
        List<String[]> messages = new ArrayList<>();

        for (String singleRecord : record) {
            String[] parsedEvent = singleRecord.split(" ");

            String command = parsedEvent[0];
            String uid = parsedEvent[1];

            if (command.equals("Enter")) {
                String nickname = parsedEvent[2];
                userMap.put(uid, nickname);
                messages.add(new String[]{uid, "ENTER"});
            } else if (command.equals("Leave")) {
                messages.add(new String[]{uid, "LEAVE"});
            } else if (command.equals("Change")) {
                String nickname = parsedEvent[2];
                userMap.put(uid, nickname);
            }
        }

        String[] answer = new String[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            String uid = messages.get(i)[0];
            String action = messages.get(i)[1];
            if (action.equals("ENTER")) {
                answer[i] = userMap.get(uid) + "님이 들어왔습니다.";
            } else {
                answer[i] = userMap.get(uid) + "님이 나갔습니다.";
            }
        }

        return answer;
    }
}
