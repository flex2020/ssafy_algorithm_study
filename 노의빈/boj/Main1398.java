package boj;

import java.io.*;
import java.util.*;

public class Main1398 {
	private static long K, answer;
	private static long[] coins;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		coins = new long[23];
		long tmp = 1;
		for (int i=0; i<7; i++) {
			coins[i*3] = (long) Math.pow(10L, i*2);
			coins[i*3 + 1] = (long) Math.pow(10L, i*2+1);
			coins[i*3 + 2] = 25L * tmp;
			tmp *= 100L;
		}
		coins[21] = (long) Math.pow(10, 14);
		coins[22] = (long) Math.pow(10, 15);
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = 0;
			K = Long.parseLong(br.readLine());
			answer += K / coins[22];
			K %= coins[22];
			answer += K / coins[21];
			K %= coins[21];
			
			for (int i=20; i>=0; i-=3) {
				// 3개 모두 범위를 초과한 경우
				if (K / coins[i] == 0 && K / coins[i-1] == 0 && K / coins[i-2] == 0) continue;
				// K가 25xxxx... 일때만 진행해봄
				// case 1~3 중 작은 것을 선택
				
				long tempAns = Long.MAX_VALUE;
				long nextK = K;
				// 25짜리 동전 0~3개 사용하면서 확인
				for (int j=0; j<4; j++) {
					if (j != 0 && K / (coins[i] * j) == 0) break;
					long tempK = K;
					long cnt = 0;
					// 25
					cnt += j;
					if (j != 0) tempK -= (coins[i] * j);
					// 10
					cnt += tempK / coins[i-1]; 
					tempK %= coins[i-1];
					
					// 1
					cnt += tempK / coins[i-2];
					tempK %= coins[i-2];

					if (tempAns > cnt) {
						tempAns = cnt;
						nextK = tempK;
					}
				}
				
				answer += tempAns == Long.MAX_VALUE ? 0 : tempAns;
				K = nextK;
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}

}
