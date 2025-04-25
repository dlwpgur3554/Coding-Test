public class Solution {
    public int solution(int[] num_list) {
        int sum = 0;
        int product = 1;

        for (int num : num_list) {
            sum += num;
            product *= num;
        }

        if (product < sum * sum) {
            return 1;
        } else {
            return 0;
        }
    }
}