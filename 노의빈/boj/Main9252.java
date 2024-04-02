package boj;

import java.io.*;
import java.util.*;

public class Main9252 {
	private static String s1, s2, answer;
	private static int[][] dp;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		dp = new int[s1.length()+1][s2.length()+1]; // dp[i] : i번째까지의 LCS 문자열을 저장
		 for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
	
		answer = "";
		
		dfs(s1.length(), s2.length());
		System.out.println(dp[s1.length()][s2.length()]);
		if (dp[s1.length()][s2.length()] > 0) System.out.println(answer);
	}
	
	private static void dfs(int n, int m) {
		if (n == 0 || m == 0) {
            return;
        }
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            answer = s1.charAt(n - 1) + answer;
            dfs(n - 1, m - 1);
        } else {
            if (dp[n-1][m] > dp[n][m-1]) {
                dfs(n-1, m);
            } else {
                dfs(n, m-1);
            }
        }
	}
}
