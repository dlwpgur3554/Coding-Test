import java.util.*;

class Solution {
    
    // 필드 이름을 배열 인덱스로 변환하는 헬퍼 함수
    private int getIndex(String field) {
        switch (field) {
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            case "remain":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid field name: " + field);
        }
    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        // 1. 필터링 기준 인덱스 및 정렬 기준 인덱스 설정
        int extIndex = getIndex(ext);
        int sortByIndex = getIndex(sort_by);
        
        // 2. 필터링
        List<int[]> filteredData = new ArrayList<>();
        
        for (int[] item : data) {
            // item[extIndex] 값이 val_ext보다 작은 데이터만 추출
            if (item[extIndex] < val_ext) {
                filteredData.add(item);
            }
        }
        
        // 3. 정렬
        // Comparator를 사용하여 sort_by에 해당하는 인덱스를 기준으로 오름차순 정렬
        // a[sortByIndex] - b[sortByIndex] : 오름차순
        filteredData.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[sortByIndex] - b[sortByIndex];
            }
        });
        
        // 4. List<int[]>를 int[][] 배열로 변환하여 반환
        return filteredData.toArray(new int[0][]);
    }
}