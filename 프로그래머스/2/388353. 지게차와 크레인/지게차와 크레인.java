import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        
        char[][] map = new char[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], '0');
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        for (String req : requests) {
            char target = req.charAt(0);
            
            if (req.length() == 2) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (map[i][j] == target) {
                            map[i][j] = '0';
                        }
                    }
                }
            } else {
                
                boolean[][] isOutside = bfsOutside(map, n, m);
                
                List<int[]> toRemove = new ArrayList<>();
                
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (map[i][j] == target) {
                            for (int d = 0; d < 4; d++) {
                                int ni = i + dr[d];
                                int nj = j + dc[d];
                                if (isOutside[ni][nj]) {
                                    toRemove.add(new int[]{i, j});
                                    break;
                                }
                            }
                        }
                    }
                }
                
                for (int[] pos : toRemove) {
                    map[pos[0]][pos[1]] = '0';
                }
            }
        }
        
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != '0') {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean[][] bfsOutside(char[][] map, int n, int m) {
        boolean[][] isOutside = new boolean[n + 2][m + 2];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0});
        isOutside[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 0 || nc < 0 || nr >= n + 2 || nc >= m + 2) continue;
                
                if (!isOutside[nr][nc] && map[nr][nc] == '0') {
                    isOutside[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return isOutside;
    }
}
