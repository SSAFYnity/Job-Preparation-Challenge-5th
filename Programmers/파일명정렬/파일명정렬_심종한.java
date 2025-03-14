import java.util.*;

class File {
    String name;
    String head;
    int number;

    public File(String name, String head, int number) {
        this.name = name;
        this.head = head;
        this.number = number;
    }
}

class Solution {
    
    int N;
    public String[] solution(String[] files) {
        N = files.length;
        String[] answer = new String[N];
        File[] arr = new File[N];
        for (int i = 0; i < N; i++) {
            int idx1 = 0, idx2 = 0;
            String str = files[i];
            // head
            while (!Character.isDigit(str.charAt(idx1))) {
                idx1++;
            }
            // number
            idx2 = idx1;
            while (idx2 < str.length() && Character.isDigit(str.charAt(idx2))) {
                idx2++;
            }
            arr[i] = new File(str, str.substring(0, idx1), Integer.parseInt(str.substring(idx1, idx2)));
        }
        Arrays.sort(arr, (o1, o2) -> {
            int headCompare = o1.head.compareToIgnoreCase(o2.head);
            if (headCompare == 0) {
                return Integer.compare(o1.number, o2.number);
            }
            return headCompare;
        });
        for (int i = 0; i < N; i++) {
            answer[i] = arr[i].name;
        }
        return answer;
    }
}
