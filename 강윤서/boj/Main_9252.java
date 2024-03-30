package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_9252 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c1 = br.readLine().toCharArray();
        char[] c2 = br.readLine().toCharArray();
        int[][] dp = new int[c1.length+1][c2.length+1];

        for (int i=1; i<c1.length+1; i++) {
            for (int j=1; j<c2.length+1; j++) {
                if (c1[i-1] == c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[c1.length][c2.length]);
        if (dp[c1.length][c2.length] != 0) {
            Stack<Character> answer = new Stack<>();
            int i = c1.length;
            int j = c2.length;
            while (i > 0 && j > 0) {
                if (dp[i][j] == dp[i-1][j]) {
                    i--;
                } else if (dp[i][j] == dp[i][j-1]) {
                    j--;
                } else if (dp[i][j] == dp[i-1][j-1]) {
                    i--;
                    j--;
                } else {
                    answer.push(c1[i-1]);
                    i--;
                    j--;
                }
            }
            while (!answer.isEmpty()) {
                System.out.print(answer.pop());
            }
        }
    }
}