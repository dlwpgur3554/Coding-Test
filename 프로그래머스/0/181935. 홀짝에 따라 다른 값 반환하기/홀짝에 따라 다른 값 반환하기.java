class Solution {
    public int solution(int n) {
        if (n % 2 == 1) {
            int sum = 0;
            for (int i = 1; i <= n; i += 2) {
                sum += i;
            }
            return sum;
        } else {
            int sum = 0;
            for (int i = 2; i <= n; i += 2) {
                sum += i * i;
            }
            return sum;
        }
    }
}