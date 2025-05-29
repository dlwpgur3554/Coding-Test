class Solution {
    public int solution(int[] players, int m, int k) {
        int ans = 0;
        int active = 0;
        int[] install = new int[24]; 

        for (int i = 0; i < 24; i++) {
            if (i >= k) {
                active -= install[i - k];
            }
            int req = players[i] / m;
            if (active < req) {
                int add = req - active;
                ans += add;
                active += add;
                install[i] = add;
            }
        }
        return ans;
    }
}