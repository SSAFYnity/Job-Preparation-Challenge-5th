import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        int n = record.length;      // 레코드 갯수
        List<String[]> log = new ArrayList();
        Map<String, String> map = new HashMap();
        for(int i=0; i<n; i++) {
            String[] cmd = record[i].split(" ");       // 공백으로 문자열을 구분
            if(cmd[0].equals("Enter")) {        // 입장
                String userId = cmd[1];     // 고유 유저 아이디
                map.put(userId, cmd[2]);    // 닉네임
                log.add(new String[]{userId, "님이 들어왔습니다."});
            } else if(cmd[0].equals("Leave")) {     // 퇴장
                log.add(new String[]{cmd[1], "님이 나갔습니다."});
            } else {        // 닉네임 변경
                map.put(cmd[1], cmd[2]);
            }
        }

        String[] answer = new String[log.size()];
        for(int i=0; i<log.size(); i++) {
            String[] str = log.get(i);
            answer[i] = map.get(str[0]) + str[1];
        }
        return answer;
    }
}