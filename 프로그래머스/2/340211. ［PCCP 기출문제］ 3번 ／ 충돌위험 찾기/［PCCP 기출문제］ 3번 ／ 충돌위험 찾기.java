import java.util.*;

class Solution {
    
    // 두 포인트 간의 최단 경로 세그먼트를 계산하는 함수 (r 우선)
    private List<int[]> getPathSegment(int[] startCoord, int[] endCoord) {
        int r1 = startCoord[0];
        int c1 = startCoord[1];
        int r2 = endCoord[0];
        int c2 = endCoord[1];

        // 경로 리스트. 첫 좌표 포함
        List<int[]> path = new ArrayList<>();
        path.add(startCoord); 

        // r, c 변화 방향 및 변화량
        int dr = (r2 > r1) ? 1 : (r2 < r1) ? -1 : 0;
        int rMoves = Math.abs(r1 - r2);
        
        int dc = (c2 > c1) ? 1 : (c2 < c1) ? -1 : 0;
        int cMoves = Math.abs(c1 - c2);
        
        int currR = r1;
        int currC = c1;
        
        // 1. r 좌표가 목표에 도달할 때까지 이동 (r 우선)
        for (int i = 0; i < rMoves; i++) {
            currR += dr;
            path.add(new int[]{currR, currC});
        }
        
        // 2. c 좌표 이동
        for (int i = 0; i < cMoves; i++) {
            currC += dc;
            path.add(new int[]{currR, currC});
        }
        
        return path;
    }

    public int solution(int[][] points, int[][] routes) {
        // 1. 포인트 정보 매핑 (1-based index -> 좌표)
        // pointsMap[i] = {r, c} for point i
        Map<Integer, int[]> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            // 포인트 번호는 1부터 시작 (i+1)
            pointMap.put(i + 1, points[i]);
        }
        
        // 2. 로봇별 전체 이동 경로 계산
        List<List<int[]>> allRobotPaths = new ArrayList<>();
        int maxTime = 0;

        for (int[] route : routes) {
            List<int[]> robotPath = new ArrayList<>();
            
            // t=0 위치: 첫 번째 포인트의 좌표
            int[] startCoord = pointMap.get(route[0]);
            robotPath.add(startCoord);
            
            // 경로의 나머지 포인트들에 대해 세그먼트 경로를 추가
            for (int i = 0; i < route.length - 1; i++) {
                int startPointNum = route[i];
                int endPointNum = route[i + 1];
                
                int[] segmentStart = pointMap.get(startPointNum);
                int[] segmentEnd = pointMap.get(endPointNum);
                
                List<int[]> segment = getPathSegment(segmentStart, segmentEnd);
                
                // segment[0]는 이미 이전 경로의 마지막 좌표와 동일하므로, 중복을 피하기 위해 t=1부터의 좌표만 추가
                for (int j = 1; j < segment.size(); j++) {
                    robotPath.add(segment.get(j));
                }
            }

            allRobotPaths.add(robotPath);
            // 로봇이 도착한 시간은 경로의 길이 - 1
            maxTime = Math.max(maxTime, robotPath.size() - 1);
        }
        
        // 3. 충돌 시뮬레이션 및 집계
        int collisionCount = 0;
        
        // t=0부터 t=maxTime까지 시뮬레이션
        for (int t = 0; t <= maxTime; t++) {
            // 현재 시간 t에서의 좌표별 로봇 수
            // 좌표를 Key로 사용하기 위해 (r, c)를 문자열 "r,c" 형태로 변환
            Map<String, Integer> coordCounts = new HashMap<>();
            
            // 모든 로봇에 대해 현재 시간 t의 위치 확인
            for (List<int[]> robotPath : allRobotPaths) {
                // 로봇이 t초에 운송 중인 경우 (경로 길이가 t+1 이상)
                if (t < robotPath.size()) {
                    int[] currentCoord = robotPath.get(t);
                    String coordKey = currentCoord[0] + "," + currentCoord[1];
                    
                    coordCounts.put(coordKey, coordCounts.getOrDefault(coordKey, 0) + 1);
                }
            }
            
            // 충돌 확인: 로봇 수가 2 이상인 좌표의 개수를 센다.
            for (int count : coordCounts.values()) {
                if (count >= 2) {
                    collisionCount++;
                }
            }
        }
        
        return collisionCount;
    }
}