import java.util.*;

class Solution {
    static final int INF = 10001;
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static int cols, rows;
    
    public int solution(String[] storage, String[] requests) {
        int remainingContainers = 0;
        rows = storage.length;
        cols = storage[0].length();
        int[][] grid = new int[cols][rows];
        
        for (int y = 0; y < cols; y++) {
            for (int x = 0; x < rows; x++) {
                grid[y][x] = storage[x].charAt(y) - 'A';
            }
        }
        
        for (String request : requests) {
            int target = request.charAt(0) - 'A';
            
            if (request.length() == 2) {
                for (int y = 0; y < cols; y++) {
                    for (int x = 0; x < rows; x++) {
                        if (grid[y][x] == target) {
                            grid[y][x] = -1;
                        }
                    }
                }
                continue;
            }
            
            int[][] distance = new int[cols][rows];
            for (int y = 0; y < cols; y++) {
                Arrays.fill(distance[y], INF);
            }
            
            List<int[]> toRemove = new ArrayList<>();
            Queue<int[]> queue = new LinkedList<>();
            
            for (int y = 0; y < cols; y++) {
                for (int x = 0; x < rows; x++) {
                    if (y != 0 && x != 0 && y != cols - 1 && x != rows - 1) continue;
                    if (distance[y][x] != INF) continue;
                    
                    if (grid[y][x] != -1) {
                        distance[y][x] = 1;
                        if (grid[y][x] == target) {
                            toRemove.add(new int[]{y, x});
                        }
                        continue;
                    }
                    
                    distance[y][x] = 0;
                    queue.add(new int[]{y, x});
                    
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int ny = current[0] + DY[d];
                            int nx = current[1] + DX[d];
                            if (!isValid(ny, nx) || distance[ny][nx] != INF) continue;
                            
                            if (grid[ny][nx] == -1) {
                                distance[ny][nx] = 0;
                                queue.add(new int[]{ny, nx});
                                continue;
                            }
                            
                            distance[ny][nx] = 1;
                            if (grid[ny][nx] == target) {
                                toRemove.add(new int[]{ny, nx});
                            }
                        }
                    }
                }
            }
            
            for (int[] cell : toRemove) {
                grid[cell[0]][cell[1]] = -1;
            }
        }
        
        for (int y = 0; y < cols; y++) {
            for (int x = 0; x < rows; x++) {
                if (grid[y][x] != -1) remainingContainers++;
            }
        }
        
        return remainingContainers;
    }
    
    private static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < cols && x < rows;
    }
}
