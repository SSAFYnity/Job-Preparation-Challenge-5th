package array.twoDimensional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 색종이붙이기_최효빈 {
    static final int SIZE = 10;
    static boolean[][] isTileOne = new boolean[SIZE][SIZE];

    static int[] papers = new int[]{0, 5, 5, 5, 5, 5};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                boolean isInputOne = Integer.parseInt(st.nextToken()) == 1;

                if(isInputOne){
                    isTileOne[i][j] = true;
                    // remainingOne++;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(isTileOne[i][j]){
                    int size = measure(i, j);
                    boolean result = usePaper(size, 1);
                    if(!result){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        // 예제입력 5번 반례.
        // 어떻게 이전 회차까지 진행 정보를 저장하고 돌아갈 것인가?
        // 어떻게 롤백하고 재귀구조를 유지할 것인가? Queue대신 리스트?
        // 실패했을 때 내가 무슨 사이즈를 썼는가 : 큰 사이즈부터 작은 사이즈까지 반복문.

        System.out.println(answer);
    }

    static boolean usePaper(int size, int number){
        // int usage = size * size * number;
        boolean result = true;


        if(papers[size] < number){
            switch (size){
                case 3:
                    result = usePaper(2, 1) && usePaper(1, 5);
                    break;
                case 2:
                    result = usePaper(1, 4);
                    break;
                default:
                    return false;
            }
        }

        if(result){
            answer += number;
            papers[size] -= number;
        }

        // remainingOne -= usage;
        return result;
    }

    static int measure(int startR, int startC){

        int offset = 0;
        Queue<int[]> visitedCoords = new ArrayDeque<>();

        while(offset < 5){
            int fixedRow = startR + offset;
            for (int col = startC; col <= startC + offset; col++) {
                if(!isTileOne[fixedRow][col])
                    return offset;
                else
                    visitedCoords.offer(new int[]{fixedRow, col});
            }

            int fixedCol = startC + offset;
            for (int row = startR; row < startR + offset; row++) {
                if(!isTileOne[row][fixedCol])
                    return offset;
                else
                    visitedCoords.offer(new int[]{row, fixedCol});
            }

            while(!visitedCoords.isEmpty()){
                int[] curr = visitedCoords.poll();
                isTileOne[curr[0]][curr[1]] = false;
            }
            offset++;
        }

        return 5;
    }
}
