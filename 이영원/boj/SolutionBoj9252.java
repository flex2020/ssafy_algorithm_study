import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	char[] str1 = br.readLine().toCharArray();
    	char[] str2 = br.readLine().toCharArray();
    	
    	int[][] dp = new int[str1.length+1][str2.length+1];
    	
    	for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if(str1[i-1]==str2[j-1]) { // 둘이 같을경우 전거에서 +1
					dp[i][j]=dp[i-1][j-1]+1;
				}else { // 다를경우 위아래 값중 더 큰걸 가져오기(길이 유지)
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
    	
//    	System.out.println("하하");
    	
    	StringBuilder sb = new StringBuilder();
    	char[] answer = new char[dp[str1.length][str2.length]];
    	int x = str1.length;
    	int y = str2.length;
    	int idx = answer.length-1;
    	while(x!=0 && y!=0) {
    		if(str1[x-1]==str2[y-1]) {
    			answer[idx--] = str1[x-1];
    			x--;
    			y--;
    		}else if(dp[x][y] == dp[x-1][y]) {
    			x--;
    		}else if(dp[x][y] == dp[x][y-1]) {
    			y--;
    		}
    	}
//    	System.out.println("하하");
    	
    	for (int i = 0; i < answer.length; i++) {
			sb.append(answer[i]);
		}
//    	System.out.println("하하");
    	
    	System.out.println(dp[str1.length][str2.length]);
    	System.out.println(sb);
    	
    }
}
