class Solution {
    
    static int[] cum;
    static int num;
    static int price;
    static double[] discount = {0.1, 0.2, 0.3, 0.4};
    
    public int[] solution(int[][] users, int[] emoticons) {
        num=0;
        price=0;
        cum = new int[users.length];
        recursive(users, emoticons, 0);
        return new int[]{num, price};
    }
    
    private static void recursive(int[][] users, int[] emoticons, int cnt){
        // basis part
        if(cnt==emoticons.length){
            int sum=0;
            int service=0;
            for(int i=0;i<users.length;i++){
                if(cum[i] >= users[i][1]) service++;
                else sum+=cum[i];
            }
            // 높은 경우 업데이트
            if(service>num || (service==num && sum>price)){
                num=service;
                price = sum;
            }
            return;
        }
        // inductive part
        for(int i=0;i<4;i++){
            // 더하기
            for(int j=0;j<users.length;j++){
                if(users[j][0]<=(i+1)*10){
                    cum[j]+=emoticons[cnt]-(int)(emoticons[cnt]*discount[i]);
                }
            }
            recursive(users, emoticons, cnt+1);
            // 원복
            for(int j=0;j<users.length;j++){
                if(users[j][0]<=(i+1)*10){
                    cum[j]-=emoticons[cnt]-(int)(emoticons[cnt]*discount[i]);
                }
            }
        }
        
    }
}
