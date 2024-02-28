package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_16236 {
	static int N, M, sharkR, sharkC, sharkSize=2, sharkCnt, answer;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1}; // 상 좌 하 우
	static class Fish {
		int r, c, size, dist;
		Fish (int r, int c, int size, int dist) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + ", dist=" + dist + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		
		Queue<int[]> Q = new ArrayDeque<>(); // bfs로 물고기 찾기
		PriorityQueue<Fish> PQ = new PriorityQueue<>((p1, p2) -> p1.dist == p2.dist ? p1.r == p2.r ? p1.c - p2.c : p1.r - p2.r : p1.dist - p2.dist);
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					sharkR = i;
					sharkC = j;
					board[i][j] = 0;
				}
			}
		}

		while (true) {
			for (int i=0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			Q.offer(new int[] {sharkR, sharkC, 0});
			visited[sharkR][sharkC] = true;
			while (!Q.isEmpty()) {
				int[] cur = Q.poll();
				for (int i=0; i<4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
						visited[nr][nc] = true;
						if (board[nr][nc] != 0) {
							if (board[nr][nc] < sharkSize) {
								PQ.offer(new Fish(nr, nc, board[nr][nc], cur[2]+1));
							} else if (board[nr][nc] == sharkSize) {
								Q.offer(new int[] {nr, nc, cur[2]+1});
							}
						} else {
							Q.offer(new int[] {nr, nc, cur[2]+1});
						}
					}
				}
			}
			if (PQ.isEmpty()) {
				break;
			}
			Fish f = PQ.poll();
			sharkR = f.r;
			sharkC = f.c;
			board[f.r][f.c] = 0; 
			sharkCnt++;
			answer += f.dist;
			PQ.clear();
			if (sharkCnt == sharkSize) {
				sharkSize += 1;
				sharkCnt = 0;
			}
		}

		System.out.println(answer);
	}

}
