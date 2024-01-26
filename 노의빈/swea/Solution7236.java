package swea;

import java.util.*;
import java.io.*;

public class Solution7236 {
	private static int N;
	private static char[][] graph;
	private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}, dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			int answer = 0;
			graph = new char[N][N];
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					graph[i][j] = st.nextToken().charAt(0);
				}
			}
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					answer = Math.max(answer, getDepth(i, j));
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
		
	}
	private static int getDepth(int x, int y) {
		int depth = 0;
		// 8방탐색
		for (int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (graph[nx][ny] == 'W') {
					depth++;
				}
			}
		}
		return depth == 0 ? 1 : depth;
	}
}
  