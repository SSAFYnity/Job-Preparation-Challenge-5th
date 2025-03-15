import java.util.*;
import java.util.regex.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};

        String[][] fileData = new String[files.length][3];
        String regex = "([a-zA-Z .-]+)(\\d+)([a-zA-Z0-9 .-]*)?";

        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < files.length; i++) {
            Matcher matcher = pattern.matcher(files[i]);

            if (matcher.matches()) {
                fileData[i][0] = matcher.group(1);
                fileData[i][1] = matcher.group(2);
                fileData[i][2] = matcher.group(3) != null ? matcher.group(3) : "";
            }
        }

        Arrays.sort(fileData, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2) {
                int headCompare = o1[0].compareToIgnoreCase(o2[0]);
                if (headCompare != 0) {
                    return headCompare;
                }

                int num1 = Integer.parseInt(o1[1].substring(0, Math.min(5, o1[1].length())));
                int num2 = Integer.parseInt(o2[1].substring(0, Math.min(5, o2[1].length())));

                return Integer.compare(num1, num2);
            }
        });

        answer = new String[files.length];
        for(int i = 0; i < files.length; i++) {
            answer[i] = fileData[i][0] + fileData[i][1] + fileData[i][2] + "";
        }

        return answer;
    }
}