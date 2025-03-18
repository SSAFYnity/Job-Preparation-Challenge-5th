import java.util.*;

public class Solution {
    public String[] solution(String[] files) {
        int n = files.length;  // 파일 개수
        Map<Integer, String[]> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringBuilder headSB = new StringBuilder();
            StringBuilder numSB = new StringBuilder();
            boolean headFlag = true;
            int numArea = 0;
            for (int j = 0; j < files[i].length(); j++) {
                char c = files[i].charAt(j);
                // 숫자고 그 범위가 5 밑이면 (99999까지가 범위라서)
                if (Character.isDigit(c) && numArea < 5) {
                    headFlag = false;
                    numArea++;
                    numSB.append(c);
                    continue;
                }
                if (headFlag) {  // head 일 때만 넣고
                    headSB.append(Character.toLowerCase(c));
                } else {  // tail 이면 break
                    break;
                }
            }
            String head = headSB.toString();  // head 부분
            String num = String.valueOf(Integer.parseInt(numSB.toString()));  // number 부분 (num으로 바꾸고 정렬하려고)
            map.put(i, new String[] {head, num});
        }

        // Entry 리스트 변환
        List<Map.Entry<Integer, String[]>> entryList = new ArrayList<>(map.entrySet());
        // 정렬(String[0] -> String[1] -> key 순서
        entryList.sort(Comparator
                .comparing((Map.Entry<Integer, String[]> entry) -> entry.getValue()[0])
                .thenComparing(entry -> Integer.parseInt(entry.getValue()[1]))
                .thenComparing(Map.Entry::getKey));
        // 정렬한 것에 맞게 리스트에 넣기
        String[] sortedFiles = new String[n];
        int idx = 0;
        for (Map.Entry<Integer, String[]> entry : entryList) {
            int key = entry.getKey();
            sortedFiles[idx++] = files[key];
        }

        return sortedFiles;
    }
}
