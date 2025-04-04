import java.util.*;
import java.io.*;

public class Main {
    static int n;       // 1 <= 전자기기의 개수 <= 10,000
    static int m;       // 1 <= 콘센트의 개수 <= 10
    static List<Integer> needTimeLst;
    static PriorityQueue<Integer> consent;

    public static int getMinTime() {
        int time = 0;       // 현재 시간
        while(!needTimeLst.isEmpty()) {
            while(!consent.isEmpty() && time == consent.peek()) {
                consent.poll();
            }
            while(consent.size() < m && !needTimeLst.isEmpty()) {
                consent.add(time + needTimeLst.remove(0));
            }
            time++;
        }

        while(!consent.isEmpty()) {
            time = consent.poll();
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        consent = new PriorityQueue();

        // 충전에 필요한 시간 입력받기
        needTimeLst = new ArrayList();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            needTimeLst.add(Integer.parseInt(st.nextToken()));
        }

        // 내림차순 정렬
        Collections.sort(needTimeLst, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 모든 전자기기를 충전하기 위한 최소 시간 출력
        System.out.print(getMinTime());
    }
}