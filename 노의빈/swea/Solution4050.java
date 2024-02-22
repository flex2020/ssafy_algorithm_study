package swea;

import java.io.*;
import java.util.*;

public class Solution4050 {
	private static int N, answer;
	private static Integer[] costs;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = 0;
			N = Integer.parseInt(br.readLine());
			costs = new Integer[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(costs, (a, b) -> b - a);
			for (int i=0; i<N; i++) {
				if (i % 3 != 2) {
					answer += costs[i];
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

}
