import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (a, b) -> {
            // 파일 이름을 HEAD와 NUMBER로 분리
            String[] partsA = splitFileName(a);
            String[] partsB = splitFileName(b);
            
            // HEAD 비교
            int headCompare = partsA[0].compareToIgnoreCase(partsB[0]);
            if (headCompare != 0) {
                return headCompare;
            }
            
            // NUMBER 비교
            int numA = Integer.parseInt(partsA[1]);
            int numB = Integer.parseInt(partsB[1]);
            return Integer.compare(numA, numB);
        });
        return files;
    }

    // 파일 이름을 HEAD와 NUMBER로 분리하는 메소드
    private String[] splitFileName(String file) {
        String head = file.split("\\d")[0];// 숫자가 나오기 전까지 HEAD 추출
        String number = file.substring(head.length()).split("\\D+")[0];// 숫자 부분 추출
        if(number.length() > 5){
            number = number.substring(0, 5);
        }
        return new String[]{head, number};
    }
}