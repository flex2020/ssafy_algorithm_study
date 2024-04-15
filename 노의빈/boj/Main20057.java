package boj;

import java.io.*;
import java.util.*;

public class Main20057 {
	private static int N, total, dist, curdist, d, dcnt;
	private static int[][] map;
	private static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		total = 0;
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		dist = 1;
		curdist = 1;
		d = 0;
		dcnt = 0;
		simulate(N/2, N/2);
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				total -= map[i][j];
			}
		}
		System.out.println(total);
	}
	
	private static void simulate(int x, int y) {
		if (x == 0 && y == 0) return;
		if (curdist == 0) {
			d = (d + 1) % 4;
			dcnt += 1;
			if (dcnt % 2 == 0) dist += 1;
			curdist = dist;
		}
		//System.out.println("(" + x + "," + y + ")");
		curdist -= 1;
		int nx = x + dx[d];
		int ny = y + dy[d];
		sandbye(x, y, nx, ny);
		simulate(nx, ny);
	}
	
	private static void sandbye(int x, int y, int nx, int ny) {
		int per1 = (int)(map[nx][ny] * 0.01 );
		int per2 = (int)(map[nx][ny] * 0.02);
		int per5 = (int)(map[nx][ny] * 0.05);
		int per7 = (int)(map[nx][ny] * 0.07);
		int per10 = (int)(map[nx][ny] * 0.1);
		int alpha = map[nx][ny] - per1 * 2 - per2 * 2 - per5 - per7 * 2 - per10 * 2;
		int d1 = (d + 1) % 4;
		int d2 = (d + 3) % 4;
		int x1 = x + dx[d1];
		int y1 = y + dy[d1];
		int x2 = x + dx[d2];
		int y2 = y + dy[d2];
		// 1퍼센트 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += per1;
		}
		if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
			map[x2][y2] += per1;
		}
		x1 = nx + dx[d1];
		y1 = ny + dy[d1];
		x2 = nx + dx[d2];
		y2 = ny + dy[d2];
		// 7퍼센트 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += per7;
		}
		if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
			map[x2][y2] += per7;
		}
		x1 = x1 + dx[d1];
		y1 = y1 + dy[d1];
		x2 = x2 + dx[d2];
		y2 = y2 + dy[d2];
		// 2퍼센트 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += per2;
		}
		if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
			map[x2][y2] += per2;
		}
		x1 = nx + dx[d] + dx[d1];
		y1 = ny + dy[d] + dy[d1];
		x2 = nx + dx[d] + dx[d2];
		y2 = ny + dy[d] + dy[d2];
		// 10퍼센트 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += per10;
		}
		if (x2 >= 0 && x2 < N && y2 >= 0 && y2 < N) {
			map[x2][y2] += per10;
		}
		x1 = nx + dx[d] * 2;
		y1 = ny + dy[d] * 2;
		// 5퍼센트 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += per5;
		}

		x1 = nx + dx[d];
		y1 = ny + dy[d];
		// alpha 계산
		if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N) {
			map[x1][y1] += alpha;
		}
		
		map[nx][ny] = 0; 
	}
}
