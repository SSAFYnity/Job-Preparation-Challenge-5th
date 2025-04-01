import java.util.*;

class Solution {
    private static void comb(ArrayList<ArrayList<Integer>> matchId, Set<Integer> set, int idx, int n, Set<String> resultSet) {
        if (set.size() == n) {
            List<Integer> sorted = new ArrayList<>(set);
            Collections.sort(sorted);
            resultSet.add(sorted.toString());
            return;
        }

        for (Integer id : matchId.get(idx)) {
            if (!set.contains(id)) {
                set.add(id);
                comb(matchId, set, idx + 1, n,resultSet);
                set.remove(id);  // 백트래킹
            }

        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        // 정규표현식으로 교체
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }
        // banned_id랑 똑같은 user_id 담을 리스트
        ArrayList<ArrayList<Integer>> matchId = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            matchId.add(new ArrayList<>());
        }
        // 돌아가면서 똑같은 아이디 idx 넣기
        for (int i = 0; i < banned_id.length; i++) {
            for (int j = 0; j < user_id.length; j++) {
                if (user_id[j].matches(banned_id[i])) {
                    matchId.get(i).add(j);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<String> resultSet = new HashSet<>();
        comb(matchId, set, 0, banned_id.length, resultSet);
        return resultSet.size();
    }
}
