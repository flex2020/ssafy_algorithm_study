import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {
	
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(br.readLine());
		int[] answer = new int[3];
		
		StringBuilder sb = new StringBuilder();
		
		dp = new int[10000][3];
		
		dp[10][2] = 1;
		dp[60][1] = 1;
		dp[300][0] = 1;
		
		if(T%10!=0) {
			System.out.println(-1);
			return;
		}
		else {
			for (int i = 20; i <= T; i+=10) {
				if(i-300>=0 && (i/300>=1 || (dp[i-300][0]!=0 || dp[i-300][1]!=0 || dp[i-300][2]!=0))) {
					dp[i][0] = dp[i-300][0]+1;
					dp[i][1] = dp[i-300][1];
					dp[i][2] = dp[i-300][2];
				}
				else if(i-60>=0 && (i/60>=1 || (dp[i-60][0]!=0 || dp[i-60][1]!=0 || dp[i-60][2]!=0))) {
					dp[i][0] = dp[i-60][0];
					dp[i][1] = dp[i-60][1]+1;
					dp[i][2] = dp[i-60][2];
				}
				else if(i-10>=0 && (i/10>=1 || (dp[i-10][0]!=0 || dp[i-10][1]!=0 || dp[i-10][2]!=0))) {
					dp[i][0] = dp[i-10][0];
					dp[i][1] = dp[i-10][1];
					dp[i][2] = dp[i-10][2]+1;
				}
			}
		}


		
		sb.append(dp[T][0]).append(" ").append(dp[T][1]).append(" ").append(dp[T][2]);
//		if(dp[T][0]==0 && dp[T][1]==0 && dp[T][2]==0) sb = new StringBuilder("-1");
//		else if(T%10!=0) sb = new StringBuilder("-1");
		
		System.out.println(sb);
		
		
	}
}
