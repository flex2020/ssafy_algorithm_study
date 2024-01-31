package com.ssafy.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
}

public class Hw3_0131 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		int[][] dp = new int[N+1][N+1];
		
		for (int i = 1; i < map.length; i++) { // map 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < map.length; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				// dp에는 0,0부터 i,j까지의 합이 들어가도록 구했다.
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
			}
		}
		
		for(int i=0;i<M;i++) {
			int answer=0;
			st = new StringTokenizer(br.readLine());
			Point p1 = new Point();
			Point p2 = new Point();
			p1.x=Integer.parseInt(st.nextToken());
			p1.y=Integer.parseInt(st.nextToken());
			p2.x=Integer.parseInt(st.nextToken());
			p2.y=Integer.parseInt(st.nextToken());
			
			// answer에는 0,0~p2.x,p2.y의 합에서 나머지 구간을 빼는 식으로 구했다.
			answer = dp[p2.x][p2.y]-dp[p1.x-1][p2.y]-dp[p2.x][p1.y-1]+dp[p1.x-1][p1.y-1];
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

}
