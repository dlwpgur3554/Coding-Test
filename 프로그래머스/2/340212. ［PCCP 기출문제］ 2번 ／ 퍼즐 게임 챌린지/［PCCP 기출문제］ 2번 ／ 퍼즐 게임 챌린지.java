class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1, right = 100000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean check(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        int prevTime = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int timeCur = times[i];
            
            if (diff <= level) {
                totalTime += timeCur;
            } else {
                int mistakes = diff - level;
                totalTime += (mistakes * (timeCur + prevTime) + timeCur);
            }
            
            prevTime = timeCur;
        }
        
        return totalTime <= limit;
    }
}