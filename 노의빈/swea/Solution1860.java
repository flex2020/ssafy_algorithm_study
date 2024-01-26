package swea;

import java.util.*;
import java.io.*;

public class Solution1860 {
	private static int N, M, K;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			String answer = "Possible";
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[] data = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				data[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(data);
			int count = 0;
			for (int i=0; i<N; i++) {
				count++;
				int bread = data[i] / M * K;
				if (bread < count) {
					answer = "Impossible";
					break;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
}