class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int min = 1, max = 100000;
        int answer = 0;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (check(diffs, times, limit, mid)) {
                answer = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return answer;
    }

    public boolean check(int[] diffs, int[] times, long limit, int level) {
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