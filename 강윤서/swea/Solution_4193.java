package 강윤서.swea;
import java.io.*;
import java.util.*;

public class Solution_4193 {
	static class State {
		int r, c, time;
		State (int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] board = new int[N][N];
			int[][] visited = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE;
				}
			}
			st = new StringTokenizer(br.readLine());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			
			PriorityQueue<State> PQ = new PriorityQueue<>((p1, p2) -> p1.time - p2.time);
			PQ.offer(new State(startR, startC, 0));
			visited[startR][startC] = 0;
			int answer = -1;
			while (!PQ.isEmpty()) {
				State cur = PQ.poll();
				if (cur.r == endR && cur.c == endC) {
					answer = cur.time;
					break;
				}
				for (int i=0; i<4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					if (0<=nr && nr<N && 0<=nc && nc<N) {
						if (board[nr][nc] == 0 && cur.time+1 < visited[nr][nc]) {
							PQ.offer(new State(nr, nc, cur.time + 1));
							visited[nr][nc] = cur.time+1;
						} else if (board[nr][nc] == 2){
							if (cur.time + (3-(cur.time%3)) < visited[nr][nc]) {
								PQ.offer(new State(nr, nc, cur.time + (3-(cur.time%3))));
								visited[nr][nc] = cur.time + (3-(cur.time%3));
							}	
						}
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

}
