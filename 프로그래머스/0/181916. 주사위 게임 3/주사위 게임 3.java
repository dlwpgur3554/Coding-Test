import java.util.*;

public class Solution {
    public static int solution(int a, int b, int c, int d) {
        int[] dice = {a, b, c, d};
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : dice) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        if (freq.size() == 1) {
            return 1111 * a;
        } else if (freq.size() == 2) {
            for (int key : freq.keySet()) {
                int count = freq.get(key);
                if (count == 3) {
                    int p = key;
                    int q = 0;
                    for (int k : freq.keySet()) {
                        if (k != p) q = k;
                    }
                    return (int) Math.pow((10 * p + q), 2);
                } else if (count == 2) {
                    List<Integer> nums = new ArrayList<>(freq.keySet());
                    int p = nums.get(0);
                    int q = nums.get(1);
                    return (p + q) * Math.abs(p - q);
                }
            }
        } else if (freq.size() == 3) {
            int two = 0;
            List<Integer> singles = new ArrayList<>();
            for (int key : freq.keySet()) {
                int count = freq.get(key);
                if (count == 2) {
                    two = key;
                } else {
                    singles.add(key);
                }
            }
            return singles.get(0) * singles.get(1);
        } else {
            return Math.min(Math.min(a, b), Math.min(c, d));
        }

        return 0;
    }
}