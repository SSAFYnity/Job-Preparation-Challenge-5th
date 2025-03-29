import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i = 0; i < places.length; i++){
            if(checkRoom(places[i])){
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    boolean checkRoom(String[] place){
        List<int[]> pList = new ArrayList<>();
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 5; c++){
                if(place[r].charAt(c) == 'P'){
                    pList.add(new int[]{r, c});
                }
            }
        }

        if(pList.size() < 2) return true;

        for(int i=0; i<pList.size()-1; i++){
            for(int j=i+1; j<pList.size(); j++){
                int r1 = pList.get(i)[0];
                int c1 = pList.get(i)[1];
                int r2 = pList.get(j)[0];
                int c2 = pList.get(j)[1];

                int dist = Math.abs(r1 - r2) + Math.abs(c1 - c2);
                if(dist > 2) continue;

                if(dist == 1){
                    return false;
                }

                if(!isPartitionBlocked(r1, c1, r2, c2, place)){
                    return false;
                }
            }
        }

        return true;
    }

    boolean isPartitionBlocked(int r1, int c1, int r2, int c2, String[] place) {
        if(r1 == r2) {
            int mid = (c1 + c2)/2;
            return place[r1].charAt(mid) == 'X';
        }
        if(c1 == c2){
            int mid = (r1 + r2)/2;
            return place[mid].charAt(c1) == 'X';
        }
        return place[r1].charAt(c2) == 'X' && place[r2].charAt(c1) == 'X';
    }
}
