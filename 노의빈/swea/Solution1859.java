package swea;

import java.util.*;
import java.io.*;

public class Solution1859 {
	private static int N;
	private static long[] future;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			long answer = 0;
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			future = new long[N];
			for (int i=0; i<N; i++) {
				future[i] = Integer.parseInt(st.nextToken());
			}
			long max = future[N-1];
			for (int i=N-2; i>=0; i--) {
				if (max > future[i]) {
					answer += max - future[i];
				} else {
					max = future[i];
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
