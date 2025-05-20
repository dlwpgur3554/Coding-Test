public class Solution {
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int mm = Integer.parseInt(parts[0]);
        int ss = Integer.parseInt(parts[1]);
        return mm * 60 + ss;
    }
    
    private String toTimeString(int seconds) {
        int mm = seconds / 60;
        int ss = seconds % 60;
        return String.format("%02d:%02d", mm, ss);
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = toSeconds(video_len);
        int currentPos = toSeconds(pos);
        int opStart = toSeconds(op_start);
        int opEnd = toSeconds(op_end);
        
        if (currentPos >= opStart && currentPos <= opEnd) {
            currentPos = opEnd;
        }
        
        for (String cmd : commands) {
            if (cmd.equals("prev")) {
                currentPos = Math.max(0, currentPos - 10);
            } else if (cmd.equals("next")) {
                currentPos = Math.min(videoLen, currentPos + 10);
            }
            
            if (currentPos >= opStart && currentPos <= opEnd) {
                currentPos = opEnd;
            }
        }
        
        return toTimeString(currentPos);
    }
}