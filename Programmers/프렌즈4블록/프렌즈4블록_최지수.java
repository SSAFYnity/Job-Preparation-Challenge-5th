import java.util.*;

class Solution {
    class WV {
        int y, x;
        public WV(int y, int x){
            this.y = y;
            this.x = x;
        }
        public int hashCode() {return 100*y + x;}
    }
    void duo(List<Character>[] list, Set<Integer>[] set) {
        int n = list.length;
        for (int i = 0; i < n; i++) {
            set[i].clear();
            for (int j = 0; j < list[i].size() - 1; j++) {
                if (list[i].get(j) == list[i].get(j+1)) {
                    set[i].add(j);
                }
            }
        }
    }
    int delete(List<Character>[] list, Set<Integer>[] set, Set<WV> que) {
        que.clear();
        for (int i = 0; i < set.length - 1; i++) {
            for (int j : set[i]) {
                if (set[i+1].contains(j) && list[i].get(j) == list[i+1].get(j)) {
                    que.add(new WV(i,j));
                    que.add(new WV(i,j+1));
                    que.add(new WV(i+1,j));
                    que.add(new WV(i+1,j+1));
                }
            }
        }
        int cnt = 0;
        for (WV now : que) {
            cnt++;
            list[now.y].remove(now.x);
        }
        return cnt;
    }
    void print(List<Character>[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
    public int solution(int n, int m, String[] board) {
        int answer = 0;
        
        List<Character>[] list = new LinkedList[m];
        Set<Integer>[] set = new HashSet[m];
        for (int i = 0; i < m; i++) {
            list[i] = new LinkedList<>();
            set[i] = new HashSet<>();
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = n-1; j >= 0; j--) {
                list[i].add(board[j].charAt(i));
            }
        }
        
        Set<WV> que = new TreeSet<>((o, t) -> t.hashCode() - o.hashCode());
        boolean keepGoing = true;
        while (keepGoing) {
            duo(list, set);
            int cnt = delete(list, set, que);
            if (cnt == 0) keepGoing = false;
            answer += cnt;
        }
        
        return answer;
    }
}