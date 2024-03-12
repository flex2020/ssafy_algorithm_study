package boj;

import java.io.*;
import java.util.*;

public class Main2116 {
	private static int N, answer;
	private static int[][] dice;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6]; // 0-5, 1-3, 2-4 마주보는면
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 첫번째 주사위의 아랫면 선택
		for (int i=0; i<6; i++) {
			int top = getAcrossNumber(0, dice[0][i]);
			int max = getMaxNumber(dice[0][i], top);
			
			// 재귀로 끝까지 주사위 쌓기
			recursive(1, top, max);
		}
		System.out.println(answer);
	}
	
	private static void recursive(int idx, int bottom, int sum) {
		// N개의 주사위를 모두 쌓았다면 종료
		if (idx == N) {
			answer = Math.max(sum, answer);
			return;
		}
		
		// 현재 시점에서 마주보는 주사위를
		int top = getAcrossNumber(idx, bottom);
		int max = getMaxNumber(bottom, top);
		recursive(idx + 1, top, sum + max);
	}
	
	
	
	private static int getAcrossNumber(int idx, int n) {
		int bIndex = 0;
		for (int i=0; i<6; i++) {
			if (dice[idx][i] == n) {
				bIndex = i;
				break;
			}
		}
		int across = 0;
		switch (bIndex) {
		case 0:
			across = dice[idx][5];
			break;
		case 1:
			across = dice[idx][3];
			break;
		case 2:
			across = dice[idx][4];
			break;
		case 3:
			across = dice[idx][1];
			break;
		case 4:
			across = dice[idx][2];
			break;
		case 5:
			across = dice[idx][0];
			break;
		}
		return across;
	}
	
	private static int getMaxNumber(int bottom, int top) {
		int max = 0;
		for (int n=6; n>=1; n--) {
			if (top != n && bottom != n) {
				max = n;
				break;
			}
		}
		return max;
	}
}
