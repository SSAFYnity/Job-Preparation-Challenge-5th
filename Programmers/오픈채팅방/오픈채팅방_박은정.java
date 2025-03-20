import java.util.*;
class Solution {
    static Map<String, String> uidToNickname;
    public String[] solution(String[] record) {
        uidToNickname = new HashMap<>();
        for(int i = record.length-1; i >= 0; i--) {
            String[] commands = record[i].split(" ");
            if(commands[0].equals("Enter") || commands[0].equals("Change")) {
                if(!uidToNickname.keySet().contains(commands[1])) {
                    uidToNickname.put(commands[1], commands[2]);
                }
            } 
        }
        
        List<String> answers = new ArrayList<>();
        for(int i = 0; i < record.length; i++){
             String[] commands = record[i].split(" ");
            if(commands[0].equals("Enter")) {
                String nickname = uidToNickname.get(commands[1]);
                answers.add(nickname+"님이 들어왔습니다.");
            } else if(commands[0].equals("Leave")) {
                String nickname = uidToNickname.get(commands[1]);
                answers.add(nickname+"님이 나갔습니다.");
            }
        }
        
        return answers.stream().toArray(String[]::new);
    }
}