import java.util.*;

public class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(friends[i], i);
        }
        
        int[][] giveCount = new int[n][n];
        int[] givenTotal = new int[n];
        int[] receivedTotal = new int[n];
        
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String giver = parts[0];
            String receiver = parts[1];
            int giverIdx = idxMap.get(giver);
            int receiverIdx = idxMap.get(receiver);
            
            giveCount[giverIdx][receiverIdx]++;
            givenTotal[giverIdx]++;
            receivedTotal[receiverIdx]++;
        }
        
        int[] nextMonthReceived = new int[n]; 
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int iToJ = giveCount[i][j];
                int jToI = giveCount[j][i];
                
                if (iToJ > jToI) {
                    nextMonthReceived[i]++;
                } else if (jToI > iToJ) {
                    nextMonthReceived[j]++;
                } else {
                    int iGiftIndex = givenTotal[i] - receivedTotal[i];
                    int jGiftIndex = givenTotal[j] - receivedTotal[j];
                    
                    if (iGiftIndex > jGiftIndex) {
                        nextMonthReceived[i]++;
                    } else if (jGiftIndex > iGiftIndex) {
                        nextMonthReceived[j]++;
                    }
                }
            }
        }
        
        int maxReceive = 0;
        for (int count : nextMonthReceived) {
            if (count > maxReceive) maxReceive = count;
        }
        
        return maxReceive;
    }
}