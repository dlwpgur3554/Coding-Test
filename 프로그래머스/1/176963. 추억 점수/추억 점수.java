import java.util.*;

public class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> scoreMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            scoreMap.put(name[i], yearning[i]);
        }

        int[] result = new int[photo.length];

        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (String person : photo[i]) {
                sum += scoreMap.getOrDefault(person, 0);
            }
            result[i] = sum;
        }

        return result;
    }
}