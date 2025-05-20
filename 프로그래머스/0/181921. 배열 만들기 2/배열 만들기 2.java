import java.util.*;

public class Solution {
    public int[] solution(int l, int r) {
        List<Integer> resultList = new ArrayList<>();
        Queue<Long> queue = new LinkedList<>();
        queue.add(5L);  // 0으로 시작하는 수는 제외 (예: 0, 05는 숫자로 안 쳐짐)
        
        while (!queue.isEmpty()) {
            long num = queue.poll();
            
            if (num > r) continue;
            if (num >= l) {
                resultList.add((int) num);
            }
            
            // 다음 자리수에 0 또는 5 추가
            long next0 = num * 10;      // 뒤에 0 붙임
            long next5 = num * 10 + 5;  // 뒤에 5 붙임
            
            if (next0 <= r) queue.add(next0);
            if (next5 <= r) queue.add(next5);
        }
        
        if (resultList.isEmpty()) {
            return new int[]{-1};
        }
        
        Collections.sort(resultList);
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }
}
