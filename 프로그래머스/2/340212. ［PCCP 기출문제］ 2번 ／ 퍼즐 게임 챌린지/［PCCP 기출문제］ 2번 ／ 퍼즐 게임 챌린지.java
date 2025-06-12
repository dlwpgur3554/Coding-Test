public class Solution {
    public static long calculateTime(int[] diffs, int[] times, int level) {
        long totalTime = 0;
        int prevTime = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int timeCur = times[i];
            
            if (diff <= level) {
                totalTime += timeCur;
            } else {
                int mistakes = diff - level;
                totalTime += (long) (mistakes * (timeCur + prevTime) + timeCur);
            }
            
            prevTime = timeCur;
        }
        
        return totalTime;
    }

    public static int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        
        while (left < right) {
            int mid = (left + right) / 2;
            long totalTime = calculateTime(diffs, times, mid);
            
            if (totalTime <= limit) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}