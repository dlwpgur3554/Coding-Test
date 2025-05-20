public class Solution {
    public int[] solution(String[] wallpaper) {
        int lux = wallpaper.length;
        int luy = wallpaper[0].length();
        int rdx = 0;
        int rdy = 0;
        
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    if (i < lux) lux = i;
                    if (j < luy) luy = j;
                    if (i + 1 > rdx) rdx = i + 1;
                    if (j + 1 > rdy) rdy = j + 1;
                }
            }
        }
        
        return new int[] {lux, luy, rdx, rdy};
    }
}