package swea;

import java.util.*;
import java.io.*;

public class Solution7964 {
	private static int N, D, answer;
	private static int[] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			map = new int[N+2];
			map[0] = 1;
			map[N+1] = 1;
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			for (int i=0; i<N+2; i++) {
				if (map[i] == 1) {
					boolean flag = true;
					// 최대 D까지 1이 없는지 확인
					for (int dx=1; dx<=D; dx++) {
						if (i+dx < N+2 && map[i+dx] == 1) {
							flag = false;
							break;
						}
					}
					if (flag && i+D < N+2) {
						answer++;
						map[i+D] = 1;
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

}
