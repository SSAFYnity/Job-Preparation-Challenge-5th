import java.util.*;
import java.util.regex.*;

class Solution {
    public String[] solution(String[] files) {
        int n = files.length;
        String[] answer = new String[n];
        String[] head = new String[n];
        int[] number = new int[n];
        PriorityQueue<Integer> que = new PriorityQueue<>((o,t) -> {
            if (head[o].equals(head[t])) {
                if (number[o] == number[t]) return o - t;
                else return number[o] - number[t];
            } else return head[o].compareTo(head[t]);
        });
        
        Pattern p = Pattern.compile("[0-9]{1,5}");
        for (int i = 0; i < n; i++) {
            Matcher m = p.matcher(files[i]);
            int idx = 0;
            if (m.find()) {
                idx = m.start();
                head[i] = files[i].substring(0, idx).toUpperCase();
                number[i] = Integer.parseInt(m.group());
            }
            que.add(i);
        }
        
        for (int i = 0; i < n; i++) {
            answer[i] = files[que.poll()];
        }
        
        return answer;
    }
}