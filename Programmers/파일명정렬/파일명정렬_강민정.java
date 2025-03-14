import java.util.Arrays;

class Solution {
    class Name implements Comparable<Name> {
        String file;        // 원본 파일명
        String head;
        int number;
        Name(String file, String head, int number) {
            this.file = file;
            this.head = head;
            this.number = number;
        }
        @Override
        public int compareTo(Name name) {
            int firstCase = head.compareToIgnoreCase(name.head);
            if(firstCase == 0) {
                return Integer.compare(number, name.number);
            }
            return firstCase;
        }
    }

    public String[] solution(String[] files) {
        int n = files.length;
        String[] answer = new String[n];
        int[][] idxArr = new int[n][2];
        Name[] name = new Name[n];
        // HEAD와 NUMBER 범위 구하기
        for(int i=0; i<n; i++) {
            int size = files[i].length();     // 현재 문자열의 길이
            // HEAD 범위 구하기
            int headIdx = 0;
            while(!Character.isDigit(files[i].charAt(headIdx))) {
                headIdx++;
            }
            idxArr[i][0] = headIdx;
            // NUMBER 범위 구하기
            int numberIdx = headIdx;
            while(size > numberIdx && Character.isDigit(files[i].charAt(numberIdx))) {
                numberIdx++;
            }
            idxArr[i][1] = numberIdx;
            name[i] = new Name(files[i], files[i].substring(0, headIdx), Integer.parseInt(files[i].substring(headIdx, numberIdx)));
        }
        Arrays.sort(name);
        for(int i=0; i<n; i++) {
            answer[i] = name[i].file;
        }
        return answer;
    }
}