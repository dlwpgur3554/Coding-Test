import java.util.*;

public class Solution {
    static int[][] info;
    static int n, m;
    static int[][][] dp;

    public static int solution(int[][] _info, int _n, int _m) {
        info = _info;
        n = _n;
        m = _m;
        int len = info.length;

        dp = new int[len + 1][n][m];
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -2);
            }
        }

        return dfs(0, 0, 0);
    }

    private static int dfs(int idx, int aTrace, int bTrace) {
        if (aTrace >= n || bTrace >= m) return -1;
        if (idx == info.length) return 0;

        if (dp[idx][aTrace][bTrace] != -2) return dp[idx][aTrace][bTrace];

        int res = Integer.MAX_VALUE;

        int aNext = dfs(idx + 1, aTrace + info[idx][0], bTrace);
        if (aNext != -1) {
            res = Math.min(res, aNext + info[idx][0]);
        }

        int bNext = dfs(idx + 1, aTrace, bTrace + info[idx][1]);
        if (bNext != -1) {
            res = Math.min(res, bNext);
        }

        if (res == Integer.MAX_VALUE) {
            dp[idx][aTrace][bTrace] = -1;
        } else {
            dp[idx][aTrace][bTrace] = res;
        }

        return dp[idx][aTrace][bTrace];
    }
}