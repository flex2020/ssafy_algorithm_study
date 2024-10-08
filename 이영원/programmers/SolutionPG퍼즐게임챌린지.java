import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left = 1;
        int right = 1;
        for(int i=0;i<diffs.length;i++){
            right = Math.max(diffs[i], right);
        }
        
        System.out.println(left + " " + right);
        
        while(left <= right){
            int mid = (left + right) / 2;
            if(limit >= calculate(diffs, times, mid)){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            // System.out.println(left + " " + right);
        }
        
        // System.out.println(left + " " + right);
        
        return left;
    }
    
    private static long calculate(int[] diffs, int[] times, int level){
        long result = 0L;
        for(int i=0;i<diffs.length;i++){
            if(diffs[i] <= level){
                result += times[i];
            }else{
                result += (diffs[i]-level)*(times[i]+times[i-1]) + times[i];
            }
            // System.out.println(result);
        }
        System.out.println(result);
        return result;
    }
    
    
}
