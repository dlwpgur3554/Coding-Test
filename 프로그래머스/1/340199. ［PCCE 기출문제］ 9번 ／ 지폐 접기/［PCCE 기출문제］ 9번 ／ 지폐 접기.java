import java.util.*;

class Solution {
    /**
     * 지갑에 넣기 위해 지폐를 최소 몇 번 접어야 하는지 계산합니다.
     * * @param wallet 지갑의 가로, 세로 크기를 담은 배열 [w_width, w_height]
     * @param bill 지폐의 가로, 세로 크기를 담은 배열 [b_width, b_height]
     * @return 지폐를 접어야 하는 최소 횟수
     */
    public int solution(int[] wallet, int[] bill) {
        // 지갑의 크기: w_min = 작은 쪽 길이, w_max = 큰 쪽 길이
        int wMin = Math.min(wallet[0], wallet[1]);
        int wMax = Math.max(wallet[0], wallet[1]);
        
        // 지폐의 크기 (복사본 사용)
        int bWidth = bill[0];
        int bHeight = bill[1];
        
        int foldCount = 0;
        
        // 반복문 조건: 지폐가 지갑에 들어가지 않는 동안 계속 접는다.
        // 지갑에 들어가는 조건: 
        // 1. (bWidth <= wMin AND bHeight <= wMax)  OR 
        // 2. (bWidth <= wMax AND bHeight <= wMin)
        
        // 들어가지 않는 조건 (NOT (1 OR 2)):
        // (bWidth > wMin OR bHeight > wMax) AND (bWidth > wMax OR bHeight > wMin)
        
        // 하지만 문제에서 제시된 의사코드를 따르는 것이 목적이므로, 더 간단한 조건으로 시작합니다.
        // 문제에서 제시된 조건: bill의 작은 값이 wallet의 작은 값 보다 크거나 bill의 큰 값이 wallet의 큰 값 보다 큰 동안
        // 이 조건은 '회전 없이' 지갑에 들어갈 수 없는 경우를 기준으로 하며, 
        // '회전'을 고려한 최종 검사는 반복문 탈출 후에도 필요할 수 있습니다.
        // 하지만 문제에서 제시된 의사코드가 최종 목표이므로, 주어진 로직을 따릅니다.

        while (true) {
            
            // 현재 지폐 크기
            int bMin = Math.min(bWidth, bHeight);
            int bMax = Math.max(bWidth, bHeight);
            
            // 2. 접기를 멈출 조건: 현재 크기로 지갑에 넣을 수 있는 경우
            // 지갑에 넣을 수 있는 조건:
            // (bWidth <= wallet[0] && bHeight <= wallet[1]) || (bWidth <= wallet[1] && bHeight <= wallet[0])
            if ((bWidth <= wMin && bHeight <= wMax) || (bWidth <= wMax && bHeight <= wMin)) {
                break;
            }
            
            // 문제의 의사코드의 반복 조건:
            // (bMin > wMin) || (bMax > wMax)
            // (단, 지갑의 크기를 미리 wMin, wMax로 정렬해 두었으므로 wallet[0], wallet[1] 대신 사용)
            // 이 조건은 로직의 단순화를 위해 사용된 것으로 보이며, 최종 검사는 지갑에 넣을 수 있는지 여부로 판단합니다.
            
            // 2-1, 2-2. 지폐를 접는 과정: 길이가 긴 쪽을 반으로 접고 소수점 이하는 버림
            if (bWidth >= bHeight) {
                // bill[0]이 bill[1]보다 크거나 같다면 (긴 쪽을 접음)
                // bill[0]을 2로 나누고 나머지는 버립니다. (Java 정수 나눗셈은 소수점 이하 버림)
                bWidth /= 2; 
            } else {
                // bill[1]을 2로 나누고 나머지는 버립니다.
                bHeight /= 2;
            }
            
            // 2-3. 접은 횟수 증가
            foldCount++;
        }
        
        return foldCount;
    }
}