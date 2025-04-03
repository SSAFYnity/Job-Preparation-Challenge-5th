import java.util.*;

class Solution {
    static int row;     // 릴레이션의 행 길이
    static int m;       // 릴레이션의 컬럼 길이
    static boolean[] visit;
    static int answer;      // 후보키의 갯수
    static String[][] relation;
    static List<String> candidate;       // 후보키
    static boolean isFind;

    public boolean isValid(List<Integer> lst) {
        Set<String> set = new HashSet();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<row; i++) {      // 행 길이만큼
            for(int j : lst) {      // 후보키를 구성하는 열의 인덱스
                sb.append(relation[i][j]);
                sb.append(" ");     // 공백 추가
            }
            set.add(sb.toString());
            sb = new StringBuffer();        // StringBuffer 초기화
        }

        // 같은 튜플이 있으면
        if(set.size() < row) {
            return false;
        }
        return true;        // 중복되는 데이터가 없다
    }

    /*
        현재 있는 후보키들과 비교하면서 부분집합이 만들어지는지 탐색
    */
    public static boolean isUnique(List<Integer> lst) {
        Set<Character> set = new HashSet();
        for(String str : candidate) {     // 후보키
            for(int i=0; i<str.length(); i++) {     // 후보키의 길이만큼
                set.add(str.charAt(i));     // 문자 추가
            }
            int cnt = 0;
            for(int i : lst) {
                if(set.contains(Character.forDigit(i, 10))) {
                    cnt++;
                }
                //set.add(Character.forDigit(i, 10));
            }
            if(cnt == Math.min(lst.size(), set.size())) {
                return false;
            }
            set.clear();        // 초기화
        }
        return true;
    }

    /*
        n: 뽑아야할 열의 갯수
        depth: 현재 뽑은 열의 갯수
        lst: 후보 키
        start: 그 다음에 뽑을 인덱스
    */
    public void dfs(int n, int depth, List<Integer> lst, int start) {
        if(n == depth) {        // n개만큼 후보 키를 구성하는 열을 뽑았다면
            if(isValid(lst) && isUnique(lst)) {      // 후보키가 될 수 있다면
                answer++;
                StringBuilder sb = new StringBuilder();
                for(int col : lst) {
                    sb.append(col);
                }
                candidate.add(sb.toString());        // 후보키에 추가
            }
            return;
        }

        for(int i=start; i<m; i++) {
            if(visit[i]) {      // 후보 키 목록에 있다면
                continue;
            }
            visit[i] = true;
            lst.add(i);
            dfs(n, depth + 1, lst, i + 1);
            visit[i] = false;
            lst.remove(lst.size() - 1);
        }
    }

    public int solution(String[][] relation) {
        answer = 0;
        m = relation[0].length;
        visit = new boolean[m];
        this.relation = relation;
        row = relation.length;
        candidate = new ArrayList();

        for(int i=1; i<=m; i++) {
            isFind = false;
            dfs(i, 0, new ArrayList(), 0);
        }

        return answer;
    }
}