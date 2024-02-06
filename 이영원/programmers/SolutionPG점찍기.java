class Solution {
    public long solution(int k, int d) {
        long square = (long) ((double)d/k/Math.sqrt(2));
        long answer=0;
        // if(d<k) return 1;
        // else if(d<(long)(Math.sqrt(2)*k)) return 3;
        answer=(square+1)*(square+1);
        long side=0;
        for(long i=square*k+k;i<=d;i+=k){
            side+=(long)Math.sqrt(((long)d*d-i*i))/k+1;
        }
        
        return answer+side+side;
    }
}
