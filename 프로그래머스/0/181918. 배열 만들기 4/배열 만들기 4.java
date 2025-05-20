import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        int i = 0;
        
        while (i < arr.length) {
            if (stk.isEmpty()) {
                stk.push(arr[i]);
                i++;
            } else {
                int top = stk.peek();
                if (top < arr[i]) {
                    stk.push(arr[i]);
                    i++;
                } else {
                    stk.pop();
                }
            }
        }
        
        // 결과를 배열로 변환 (스택은 LIFO이므로 뒤집어서 반환)
        int[] result = new int[stk.size()];
        for (int j = stk.size() - 1; j >= 0; j--) {
            result[j] = stk.pop();
        }
        
        return result;
    }
}
