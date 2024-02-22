package boj;

import java.io.*;
import java.util.*;

public class Main17070 {
	private static int N, answer;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		recursive(1, 1, 1, 2);
		
		System.out.println(answer);
	}
	private static void recursive(int x1, int y1, int x2, int y2) {
		// 한쪽 끝이 도착했다면 종료
		if (x1 == N && y1 == N || x2 == N && y2 == N) {
			answer += 1;
			return;
		}
		
		// 1. 파이프가 가로로 놓여져 있는 경우
		if (x1 == x2) {
			// 오른쪽 자리가 비어있는지 확인 후 재귀
			if (y2 + 1 <= N && map[x2][y2+1] == 0) {
				recursive(x2, y2, x2, y2 + 1);
			}
			
			// 오른쪽, 대각선아래, 아래가 비어있는지 확인 후 재귀
			if (x2 + 1 <= N && y2 + 1 <= N && map[x2][y2+1] == 0 && map[x2+1][y2] == 0 && map[x2+1][y2+1] == 0) {
				recursive(x2, y2, x2 + 1, y2 + 1);
			}
		}	
		// 2. 파이프가 세로로 놓여져 있는 경우
		else if (y1 == y2) {
			// 아래 자리가 비어있는지 확인 후 재귀
			if (x2 + 1 <= N && map[x2+1][y2] == 0) {
				recursive(x2, y2, x2 + 1, y2);
			}
			
			// 오른쪽, 대각선아래, 아래가 비어있는지 확인 후 재귀
			if (x2 + 1 <= N && y2 + 1 <= N && map[x2][y2+1] == 0 && map[x2+1][y2] == 0 && map[x2+1][y2+1] == 0) {
				recursive(x2, y2, x2 + 1, y2 + 1);
			}
		}
		// 3. 파이프가 대각선으로 놓여져 있는 경우
		else if (x1 + 1 == x2 && y1 + 1 == y2) {
			// 오른쪽 자리가 비어있는지 확인 후 재귀
			if (y2 + 1 <= N && map[x2][y2+1] == 0) {
				recursive(x2, y2, x2, y2 + 1);
			}
			// 아래 자리가 비어있는지 확인 후 재귀
			if (x2 + 1 <= N && map[x2+1][y2] == 0) {
				recursive(x2, y2, x2 + 1, y2);
			}
			// 오른쪽, 대각선아래, 아래가 비어있는지 확인 후 재귀
			if (x2 + 1 <= N && y2 + 1 <= N && map[x2][y2+1] == 0 && map[x2+1][y2] == 0 && map[x2+1][y2+1] == 0) {
				recursive(x2, y2, x2 + 1, y2 + 1);
			}
		}

	}
}
