package swea;

import java.util.*;
import java.io.*;

public class Solution1940 {
	private static int N, answer, v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;
			v = 0;
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int amount = 0;
				switch (op) {
					case 1:
						amount = Integer.parseInt(st.nextToken());
						v += amount;
						break;
					case 2:
						amount = Integer.parseInt(st.nextToken());
						v -= amount;
						break;
				}
				if (v < 0) v = 0;
				answer += v;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

}
