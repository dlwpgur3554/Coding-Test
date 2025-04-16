class Solution {
    public int solution(int a, int b, boolean flag) {
        if(flag == true){
            return a + b;
        }
        else if(flag == false){
            return a - b;
        }
        return 0;
    }
}