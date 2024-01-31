package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 설탕 배달 : dp
public class Question3 {
    static int n;
    static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    n = Integer.parseInt(br.readLine());
	//        greedy(n);
	
	//        recursive(n, 0);
	//        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	
	    dp();
	}
	
	// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	// x x 1 x 1 2 x 2 3 2 3 4 3 4 3
	// Math.min(dp[n-3] + 1, dp[n-5] + 1)
	// 각 무게 당 필요한 봉지 수를 저장할 배열을 선언합니다
	private static void dp() {
	    int[] dp = new int[n + 1];
	    Arrays.fill(dp, 20000);
	    dp[3] = 1;
	    if (n >= 5) {
	        dp[5] = 1;
	    	for (int i = 6; i <= n; i++) {
	    		dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);            
	    	}
	    }
    	System.out.println(dp[n] >= 20000 ? -1 : dp[n]);
	}
	
	private static void recursive(int n, int cnt) {
	    // basis part
	    if (n < 0) {
	        return;
	    }
	    if (n == 0) {
	        ans = Math.min(ans, cnt);
	        return;
	    }
	
	    // inductive part
	    recursive(n - 5, cnt + 1);
	    recursive(n - 3, cnt + 1);
	}
	//    private static void greedy(int n) {
	//        int cnt = 0;
	//
	//        while(n%5 != 0) {
	//            n -= 3;
	//            cnt++;
	//        }
	//        if(n<0) {
	//            System.out.println(-1);
	//        } else {
	//            cnt += n/5;
	//            System.out.println(cnt);
	//        }
	//    }
}
