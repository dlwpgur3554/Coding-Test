import java.util.*;

public class Solution {
    public List<Integer> solution(int n) {
        List<Integer> sequence = new ArrayList<>();
        int x = n;
        sequence.add(x);
        
        while (x != 1) {
            if (x % 2 == 0) {
                x = x / 2;
            } else {
                x = 3 * x + 1;
            }
            sequence.add(x);
        }
        
        return sequence;
    }
}