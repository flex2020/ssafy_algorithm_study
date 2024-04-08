class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int idx = deliveries.length-1;
        while(idx>=0){
            // 아무것도 없는 경우 하나 내리고 다시 진행
            if(deliveries[idx]==0 && pickups[idx]==0){
                idx--;
                continue;
            }
            // 최대거리 답에 더하기
            answer += (idx+1)*2;
            int nCap = cap;
            int nIdx=idx;
            int nIdx2=idx;
            
            for(int i=idx;i>=0;i--){ // 배달 수 count
                if(deliveries[i]==0) continue;
                if(deliveries[i]==nCap){
                    deliveries[i]-=nCap;
                    nIdx=i-1;
                    break;
                }else if(deliveries[i]/nCap>0){
                    deliveries[i]-=nCap;
                    nIdx=i;
                    break;
                }else{
                    nCap-=deliveries[i];
                    deliveries[i]=0;
                }
                
                // if(i==0) nIdx=0;
            }
            
            nCap = cap;
            
            for(int i=idx;i>=0;i--){ // 수거 수 count
                if(pickups[i]==0) continue;
                if(pickups[i]==nCap){
                    pickups[i]-=nCap;
                    nIdx2=i-1;
                    break;
                }else if(pickups[i]/nCap>0){
                    pickups[i]-=nCap;
                    nIdx2=i;
                    break;
                }else{
                    nCap-=pickups[i];
                    pickups[i]=0;
                }
                
                // if(i==0) nIdx2=0;
            }
            
            idx = Math.max(nIdx, nIdx2);
            
        }
        return answer;
    }
}
