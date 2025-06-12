public class Solution {
    public static int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        int INF = 999999;
        
        // dp[i][j] = i번째 물건까지 사용했을 때 B 흔적 j일 때의 A 도둑 최소 흔적
        int[][] dp = new int[itemCount + 1][m];
        
        for (int i = 0; i <= itemCount; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0; // 초기 상태

        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int j = 0; j < m; j++) {
                if (dp[i][j] == INF) continue;

                // A 도둑이 훔치는 경우
                int aSum = dp[i][j] + aTrace;
                if (aSum < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], aSum);
                }

                // B 도둑이 훔치는 경우
                int bSum = j + bTrace;
                if (bSum < m) {
                    dp[i + 1][bSum] = Math.min(dp[i + 1][bSum], dp[i][j]);
                }
            }
        }

        int minA = INF;
        for (int j = 0; j < m; j++) {
            minA = Math.min(minA, dp[itemCount][j]);
        }

        return minA == INF ? -1 : minA;
    }
}