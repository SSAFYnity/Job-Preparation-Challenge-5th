import java.util.*;

class Solution {
    int r, c;
    PriorityQueue<Integer> que;
    HashSet<Integer> candi = new HashSet<>();
    public boolean check(int num, String[][] relation) {
        ArrayList<Integer> checklist = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < c; i++) {
            if (isUsed(num, i)) checklist.add(i);
        }
        
        for (String[] rel : relation) {
            StringBuilder temp = new StringBuilder();
            for (int c : checklist) temp.append(rel[c]);
            set.add(temp.toString());
        }
        return set.size() == r;
    }
    public int bit(int o, int i) { return o + (1 << (c-i-1)); }
    public boolean isUsed(int o, int i) { return (o & (1 << (c-i-1))) != 0; }
    public boolean isContained(int big, int small) { return (big & small) == small; }
    public boolean isMin(int num) {
        for (int c : candi) {
            if (isContained(num, c)) return false;
        }
        return true;
    }
    public int solution(String[][] relation) {
        int answer = 0;
        // 유일성을 가지는 최소단위 갯수
        r = relation.length;
        c = relation[0].length;
        que = new PriorityQueue<>();
        for (int i = 0; i < c; i++) {
            if (!check(bit(0, i), relation)) que.add(bit(0, i));
            else candi.add(bit(0, i));
        }
        while (!que.isEmpty()) {
            int now = que.poll();
            // 지금 채택한 거 보다 더 뒤에 있는 거만 보게 하고 싶어
            for (int i = c-1; i >= 1; i--) {
                if (isUsed(now, i)) break;
                int next = bit(now, i);
                if (check(next, relation)) {
                    if (isMin(next)) candi.add(next);
                }
                else que.add(next);
            }
        }
        return candi.size();
    }
}