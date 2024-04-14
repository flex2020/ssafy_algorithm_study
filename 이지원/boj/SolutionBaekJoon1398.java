import java.util.*;
import java.io.*;

public class SolutionBaekJoon1398 {
	
	static int[] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		memo = new int[100];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		memo[1] = 1;
		memo[10] = 1;
		memo[25] = 1;
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			long n = Long.parseLong(br.readLine());
			
			int result = 0;
			while (true) {
				result += calculateCnt((int)(n % 100));
				
				if (n / 100 == 0) {
					break;
				} else {
					n /= 100;
				}
			}
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static int calculateCnt(int n) {
		if (memo[n] != -1) {
			return memo[n];
		}
		
		int min = Integer.MAX_VALUE;
		if (n - 1 > 0) {
			min = Math.min(min, calculateCnt(n - 1) + 1);
		}
		
		if (n - 10 > 0) {
			min = Math.min(min, calculateCnt(n - 10) + 1);
		}
		
		if (n - 25 > 0) {
			min = Math.min(min, calculateCnt(n - 25) + 1);
		}
		
		return memo[n] = min;
	}

}
