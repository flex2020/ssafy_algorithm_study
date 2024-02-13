class Solution {
    public long solution(int k, int d) {
        long answer = 0L;
        for (long x=0L; x<=d; x+=k) {
            answer += (long) Math.sqrt((long)d*d - x*x) / (long) k + 1;
        }
        
        return answer;
    }
}
