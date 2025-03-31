import java.util.*;
import java.io.*;


public class Main {
    static class Member {
        int no;
        int value;
        
        Member(int no, int value) {
            this.no = no;
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        for (int c = 0; c < C; c++) {
            int[][] skills = new int[11][11];
            
            
            
            StringTokenizer st;
            // 포지션별 멤버
            List<Member>[] positions = new ArrayList[11];
            for (int i = 0; i < 11; i++) {
                positions[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    skills[i][j] = Integer.parseInt(st.nextToken());
                    if (skills[i][j] != 0) {
                        positions[j].add(new Member(i, skills[i][j]));
                    }
                }
            }
            
            
            boolean[] pickedMember = new boolean[11];
            int answer = getMax(11, 0, 0, -1, positions, pickedMember);
            
            System.out.println(answer);
            
            
        }
    }
    
    
    // 뽑아야 할 최종 개수, 뽑은 개수, 지금까지의 합, 포지션별 멤버, 뽑은 멤버
    public static int getMax(int target, int pickedCount, int sum, int currentMax, List<Member>[] positions, boolean[] pickedMember) {
        if (pickedCount == target) {
            if (sum > currentMax) {
                return sum;
            }
            return currentMax;
        }
        
        
        // pickedCount번째 포지션에서 뛸 수 있는 멤버들
        List<Member> members = positions[pickedCount];
        
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            
            if (pickedMember[m.no]) {
                continue;
            }
            
            pickedMember[m.no] = true;
            currentMax = getMax(target, pickedCount + 1, sum + m.value, currentMax, positions, pickedMember);
            pickedMember[m.no] = false;
        }
        
        return currentMax;
    }
}
