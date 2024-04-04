package 강윤서.swea;
import java.io.*;
import java.util.*;

public class Solution_1249 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		for (int tc=1; tc<=T; tc++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			for (int i=0; i<N; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j=0; j<N; j++) {
					board[i][j] = input[j] - '0';
				}
			}
			visited[0][0] = true;
			PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt((p) -> p[2]));
			PQ.offer(new int[] {0, 0, 0});
			while (!PQ.isEmpty()) {
				int[] cur = PQ.poll();
				if (cur[0] == N-1 && cur[1] == N-1) {
					answer = cur[2];
					break;
				}
				for (int i=0; i<4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
						PQ.offer(new int[] {nr, nc, cur[2] + board[nr][nc]});
						visited[nr][nc] = true;
					}
				}
			}
			sb.append("#" + tc +" " + answer + "\n");
		}
		System.out.println(sb);

	}

}
