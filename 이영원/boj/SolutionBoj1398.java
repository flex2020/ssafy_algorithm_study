import java.util.*;
import java.io.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < T; i++) {
    		int[] dp = new int[100];
			long num = Long.parseLong(br.readLine());
			int answer=0;
			// 1, 10, 25 단위로 3개씩 끊어서 각 0~99에서의 최소 수를 구한 다음에 반복
			for (int j = 1; j < 100; j++) {
				if(j>=25) {
					dp[j] = Math.min(dp[j-1]+1, dp[j-10]+1);
					dp[j] = Math.min(dp[j], dp[j-25]+1);
				}else if(j>=10) {
					dp[j] = Math.min(dp[j-1]+1, dp[j-10]+1);
				}else {
					dp[j]=j;
				}
			}
			
			while(num!=0) {
				answer+=dp[(int)(num%100)];
				num/=100;
			}
			sb.append(answer).append("\n");
		}
    	System.out.println(sb);
    	
    }
}
