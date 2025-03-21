import java.util.*;

/*
아이디어 : 분리후 정렬
1. head/number 분리
2. head, number, index 기준 오름차순 정렬
3. 반환
*/

class Solution {
    class File implements Comparable<File> {
        String head;
        int number;
        int index;
        
        public File(String head, int number, int index){
            this.head = head;
            this.number = number;
            this.index = index;
        }
        
        @Override
        public int compareTo(File f){
            if(this.head.equals(f.head)){
                if(this.number == f.number){
                    return this.index - f.index;
                }else return this.number - f.number;
            }
            return this.head.compareTo(f.head);
        }
    }
    
    ArrayList<File> list = new ArrayList<>();
    public String[] solution(String[] files) {
        // 1. 분리하기
        getFiles(files);
        
        // 2.정렬하기
        Collections.sort(list);
        
        String[] answer = new String[files.length];
        for(int i = 0; i < list.size(); i++){
            answer[i] = files[list.get(i).index];
        }
        
        return answer;
    }
    
    void getFiles(String[] files){   // head끝,number 시작 끝 인덱스 반환
        for(int i = 0; i < files.length; i++){
            int nextIdx = 0;    // 부분의 시작 인덱스
            String str = "";
            String nums = "";
            
            // 1. head 구하기
            for(int idx = 0; idx < files[i].length(); idx++){
                if(Character.isDigit(files[i].charAt(idx))){  // 첫 숫자 등장
                    str = files[i].substring(0, idx);
                    nextIdx = idx;
                    break;
                }
            }
            
            // 2. number 구하기
            int cnt = 0;
            int nIdx = 0;   // NUMBER 마지막 인덱스
            for(int idx = nextIdx; idx < files[i].length(); idx++){
                if(Character.isDigit(files[i].charAt(idx))){  // 첫 숫자 등장
                    cnt++;
                    nIdx = idx + 1;
                    if(cnt > 5) break;
                }else break;
            }
            
            nums = files[i].substring(nextIdx, nIdx);
            list.add(new File(str.toUpperCase(), Integer.parseInt(nums), i));
        }
    }
}