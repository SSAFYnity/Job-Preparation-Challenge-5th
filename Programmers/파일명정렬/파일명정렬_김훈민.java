import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        int count = 0;
        File[] fileClasses = new File[files.length];
        for(String file : files){
            Parse p = findParseIdx(file);
            File fileClass = new File(file.substring(0, p.startIdx), file.substring(p.startIdx, p.endIdx), file.substring(p.endIdx, file.length()), count);
            fileClasses[count++] = fileClass;
        }
        Arrays.sort(fileClasses);
        String[] answer = new String[files.length];
        count = 0;
        for(File f : fileClasses){
            answer[count++] = f.toString();
        }
        return answer;
    }

    static class File implements Comparable<File>{
        String head;
        String number;
        String tail;
        int idx;

        File(String head, String number, String tail, int idx){
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.idx = idx;
        }

        @Override
        public int compareTo(File o){
            int compareHead = this.head.compareToIgnoreCase(o.head);
            int compareNumber = Integer.parseInt(this.number);
            compareNumber = Integer.compare(compareNumber, Integer.parseInt(o.number));
            if(compareHead == 0){
                if(compareNumber == 0){
                    return Integer.compare(this.idx, o.idx);
                }
                return compareNumber;
            }
            return compareHead;
        }

        @Override
        public String toString(){
            return head + number  + tail;
        }
    }

    static Parse findParseIdx(String s){
        int startIdx = 0;
        int endIdx = s.length();
        boolean flag = false;
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                if(!flag){
                    flag = true;
                    startIdx = i;
                }
            }
            if(!Character.isDigit(s.charAt(i))){
                if(flag){
                    endIdx = i;
                    break;
                }
            }
        }
        return new Parse(startIdx, endIdx);
    }

    static class Parse{
        int startIdx = 0;
        int endIdx = 0;

        Parse(int startIdx, int endIdx){
            this.startIdx = startIdx;
            this.endIdx = endIdx;
        }
    }
}