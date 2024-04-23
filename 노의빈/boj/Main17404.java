package boj;

import java.io.*;
import java.util.*;

public class Main17404 {
	private static int N;
	private static int[][] cost, red, green, blue;
	private static final int MAX = 1000 * 1000 + 1;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][3]; // 빨강 초록 파랑 순
		red = new int[N+1][3];
		green = new int[N+1][3];
		blue = new int[N+1][3];
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		// dp[i][j] : 시작이 dp이고, i번째를 j색으로 칠했을때의 최소 비용
		// dp 무한대 초기화
		for (int i=1; i<=N; i++) {
			for (int j=0; j<3; j++) {
				red[i][j] = MAX;
				green[i][j] = MAX;
				blue[i][j] = MAX;
			}
		}
		// dp 초기값 세팅
		red[1][0] = cost[1][0];
		green[1][1] = cost[1][1];
		blue[1][2] = cost[1][2];

		red[2][1] = red[1][0] + cost[2][1];
		red[2][2] = red[1][0] + cost[2][2];
		green[2][0] = green[1][1] + cost[2][0];
		green[2][2] = green[1][1] + cost[2][2];
		blue[2][0] = blue[1][2] + cost[2][0];
		blue[2][1] = blue[1][2] + cost[2][1];
		
		for (int i=3; i<=N-1; i++) {
			for (int j=0; j<3; j++) {
				int color1 = (j + 1) % 3;
				int color2 = (j + 2) % 3;
				red[i][j] = Math.min(red[i-1][color1], red[i-1][color2]) + cost[i][j];
				blue[i][j] = Math.min(blue[i-1][color1], blue[i-1][color2]) + cost[i][j];
				green[i][j] = Math.min(green[i-1][color1], green[i-1][color2]) + cost[i][j];
			}
		}
		
		red[N][1] = Math.min(red[N-1][0], red[N-1][2]) + cost[N][1]; 
		red[N][2] = Math.min(red[N-1][0], red[N-1][1]) + cost[N][2];
		green[N][0] = Math.min(green[N-1][1], green[N-1][2]) + cost[N][0];
		green[N][2] = Math.min(green[N-1][0], green[N-1][1]) + cost[N][2];
		blue[N][0] = Math.min(blue[N-1][1], blue[N-1][2]) + cost[N][0];
		blue[N][1] = Math.min(blue[N-1][0], blue[N-1][2]) + cost[N][1];
		
		int answer = Integer.MAX_VALUE;
		for (int i=0; i<3; i++) {
			answer = Math.min(answer, red[N][i]);
			answer = Math.min(answer, green[N][i]);
			answer = Math.min(answer, blue[N][i]);
		}
		System.out.println(answer);
	}

}
