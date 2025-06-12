import java.util.Arrays;

public class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        int INF = 999999;
        int[][] dp = new int[itemCount + 1][m];
        
        for (int i = 0; i <= itemCount; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == INF) continue;

                int nextATrace = dp[i][j] + aTrace;
                if (nextATrace < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], nextATrace);
                }

                int nextBTrace = j + bTrace;
                if (nextBTrace < m) {
                    dp[i + 1][nextBTrace] = Math.min(dp[i + 1][nextBTrace], dp[i][j]);
                }
            }
        }

        int result = INF;
        for (int j = 0; j < m; j++) {
            result = Math.min(result, dp[itemCount][j]);
        }

        return result == INF ? -1 : result;
    }
}