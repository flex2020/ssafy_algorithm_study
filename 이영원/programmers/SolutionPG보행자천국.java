class Solution {
    static int MOD = 20170805;
    
    static int[] dx = {0, 1}; // 우 하
    static int[] dy = {1, 0}; // 우 하
    
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m][n][2];
        
        for(int i=0;i<m;i++){
            if(cityMap[i][0]==1) break;
            dp[i][0][1] = 1;
        }
        
        for(int i=0;i<n;i++){
            if(cityMap[0][i]==1) break;
            dp[0][i][0] = 1;
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(cityMap[i-1][j]==0){
                    dp[i][j][1]+=(dp[i-1][j][0]+dp[i-1][j][1])%MOD;
                }else if(cityMap[i-1][j]==2){
                    dp[i][j][1]+=dp[i-1][j][1]%MOD;
                }
                
                if(cityMap[i][j-1]==0){
                    dp[i][j][0]+=(dp[i][j-1][0]+dp[i][j-1][1])%MOD;
                }else if(cityMap[i][j-1]==2){
                    dp[i][j][0]+=dp[i][j-1][0]%MOD;
                }
            }
        }
        
        // print(dp, m, n);
        
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1])%MOD;
    }
}
