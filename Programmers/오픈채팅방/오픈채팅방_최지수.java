import java.util.*;

class Solution {
    
    class Text {
        String userId;
        boolean isEnter;
        public Text(String userId, boolean isEnter) {
            this.userId = userId;
            this.isEnter = isEnter;
        }
    }
    
    public String[] solution(String[] record) {
        
        Map<String, String> map = new HashMap<>();
        Queue<Text> que = new ArrayDeque<>();
        
        for (String str : record) {
            String[] arr = str.split(" ");
            if (arr[0].equals("Enter")) {
                que.add(new Text(arr[1], true));
                map.put(arr[1], arr[2]);
            } else if (arr[0].equals("Leave")) {
                que.add(new Text(arr[1], false));
            } else {
                map.put(arr[1], arr[2]);
            }
        }
        
        String[] answer = new String[que.size()];
        int i = 0;
        while (!que.isEmpty()) {
            Text text = que.poll();
            answer[i++] = map.get(text.userId) + (text.isEnter ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }
        
        return answer;
    }
}