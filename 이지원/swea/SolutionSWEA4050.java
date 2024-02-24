import java.util.*;
import java.io.*;

public class SolutionSWEA4050 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			// 옷 금액 입력받기
			int[] prices = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < prices.length; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(prices);
			
			int count = 1;
			int totalPrice = 0;
			for (int i = prices.length - 1; i > -1; i--) {
				if (count % 3 != 0) {
					totalPrice += prices[i];
				}
				count++;
			}
			
			System.out.println("#" + testCase + " " + totalPrice);
		}
	}

}
