import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    static List<List<Integer>> lst;     // 인접 리스트
    static int[] time;

    class City {
        int num;
        int distance;
        City(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    public void bfs(int destination, int n) {
        Queue<City> que = new LinkedList();
        que.add(new City(destination, 0));
        while(!que.isEmpty()) {
            City city = que.poll();
            for(int i : lst.get(city.num)) {
                if(time[i] == -1) {
                    que.add(new City(i, city.distance + 1));
                    time[i] = city.distance + 1;
                }
            }
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        time = new int[n + 1];
        int[] answer = new int[sources.length];
        Arrays.fill(time, -1);
        time[destination] = 0;

        // 인접 리스트 생성
        lst = new ArrayList();
        for(int i=0; i<=n; i++) {
            lst.add(new LinkedList());
        }
        for(int i=0; i<roads.length; i++) {
            lst.get(roads[i][0]).add(roads[i][1]);
            lst.get(roads[i][1]).add(roads[i][0]);
        }

        bfs(destination, n);

        for(int i=0; i<sources.length; i++) {
            answer[i] = time[sources[i]];
        }

        return answer;
    }
}