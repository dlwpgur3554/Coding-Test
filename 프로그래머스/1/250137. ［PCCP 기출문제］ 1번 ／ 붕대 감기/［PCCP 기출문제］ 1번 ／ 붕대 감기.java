import java.util.*;

class Solution {
    /**
     * 붕대 감기 기술과 몬스터 공격 패턴을 시뮬레이션하여 최종 체력을 계산합니다.
     *
     * @param bandage [시전 시간 t, 초당 회복량 x, 추가 회복량 y]
     * @param health 캐릭터의 최대 체력
     * @param attacks [공격 시간, 피해량] 목록
     * @return 최종 체력. 캐릭터 사망 시 -1
     */
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 붕대 감기 기술 정보
        int t = bandage[0]; // 시전 시간 (연속 성공 시간)
        int x = bandage[1]; // 초당 회복량
        int y = bandage[2]; // 추가 회복량
        
        int maxHealth = health; // 최대 체력
        int currentHealth = health; // 현재 체력
        int successTime = 0; // 붕대 감기 연속 성공 시간
        
        // 몬스터 공격 정보를 {시간: 피해량} 맵으로 변환하여 O(1)에 공격 여부 확인
        Map<Integer, Integer> attackMap = new HashMap<>();
        int lastAttackTime = 0;
        for (int[] attack : attacks) {
            attackMap.put(attack[0], attack[1]);
            lastAttackTime = Math.max(lastAttackTime, attack[0]);
        }
        
        // 시간 t=1부터 마지막 공격 시간까지 시뮬레이션
        for (int time = 1; time <= lastAttackTime; time++) {
            
            // 1. 공격 여부 확인
            if (attackMap.containsKey(time)) {
                // 몬스터 공격이 있는 경우
                int damage = attackMap.get(time);
                
                // 1-1. 피해량만큼 체력 감소
                currentHealth -= damage;
                
                // 1-2. 기술 취소 및 연속 성공 초기화
                successTime = 0;
                
                // 1-3. 캐릭터 사망 확인
                if (currentHealth <= 0) {
                    return -1; // 사망
                }
                
            } else {
                // 2. 공격이 없는 경우 (회복)
                
                // 2-1. 초당 회복량만큼 체력 회복
                currentHealth += x;
                
                // 2-2. 연속 성공 시간 증가
                successTime++;
                
                // 2-3. 연속 성공 보너스 확인
                if (successTime == t) {
                    currentHealth += y; // 추가 회복
                    successTime = 0;    // 연속 성공 시간 초기화
                }
                
                // 2-4. 체력이 최대 체력을 초과하지 않도록 제한
                if (currentHealth > maxHealth) {
                    currentHealth = maxHealth;
                }
            }
        }
        
        // 모든 공격이 끝난 직후 남은 체력 반환
        return currentHealth;
    }
}