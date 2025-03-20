import java.util.*;

class Solution {
    
    int N, M;
    Map<String, String> map = new HashMap<>();
    
    public String[] solution(String[] record) {
        N = record.length;
        M = 0;
        for (int i = 0; i < N; i++) {
            String[] recordArr = record[i].split(" ");
            String cmd = recordArr[0];
            String id = recordArr[1];
            if ("Enter".equals(cmd) || "Change".equals(cmd)) {
                map.put(id, recordArr[2]);   
            }
            if (!"Change".equals(cmd)) {
                M++;
            }
        }
    
        String[] answer = new String[M];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            String[] recordArr = record[i].split(" ");
            String cmd = recordArr[0];
            String id = recordArr[1];
            if ("Enter".equals(cmd)) {
                answer[idx++] = map.get(id) + "님이 들어왔습니다.";
            } else if ("Leave".equals(cmd)) {
                answer[idx++] = map.get(id) + "님이 나갔습니다.";
            }
        }
        return answer;
    }
}
