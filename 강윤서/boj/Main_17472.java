package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_17472 {
	static int N, M, number = 2, islandCnt;
	static int[][] board;
	static PriorityQueue<int[]> PQ;
	static int[][] dist;
	static boolean[] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		PQ = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 1. 섬 넘버링
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j] == 1) {
					dfs(i, j);
					number++;
					islandCnt++;
				}
			}
		}
		
		// 2. 각 섬에서 다른 섬까지 연결해보기
		dist = new int[number][number];
		for (int i=2; i<number; i++) {
			Arrays.fill(dist[i], 987654321);
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j] > 0) {
					for (int d=0; d<4; d++) {
						bfs(i, j, d);
					}
				}
			}
		}
		
		int answer = 0;
		int cnt = 0;
		visited = new boolean[number];
		PQ.offer(new int[] {2, 0});
		while (!PQ.isEmpty()) {
			int[] cur = PQ.poll();
			if (visited[cur[0]]) continue;
			visited[cur[0]] = true;
			cnt++;
			answer += cur[1];
			for (int i=2; i<number; i++) {
				if (i == cur[0]) continue;
				if (dist[cur[0]][i] >= 987654321) continue;
				PQ.offer(new int[] {i, dist[cur[0]][i]});
			}
		}
		if (cnt == islandCnt)
			System.out.println(answer);
		else
			System.out.println(-1);
	}
	private static void bfs(int r, int c, int i) {
		Queue<int[]> Q = new ArrayDeque<>();
		int startNumber = board[r][c];
		Q.offer(new int[] {r, c, 0});
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			
			if (board[cur[0]][cur[1]] > 0 && startNumber != board[cur[0]][cur[1]]) {
				if (cur[2] - 1 < 2) return ;
				int endNumber = board[cur[0]][cur[1]];
				
				dist[startNumber][endNumber] = Math.min(dist[startNumber][endNumber], cur[2] - 1);
				dist[endNumber][startNumber] = Math.min(dist[endNumber][startNumber], cur[2] - 1);
				return ;
			}
			int nr = cur[0] + dr[i];
			int nc = cur[1] + dc[i];
			if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] != startNumber) {
				Q.offer(new int[] {nr, nc, cur[2]+1});
			}
		}
	}
	public static void dfs(int r, int c) {
		board[r][c] = number;
		for (int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}
}
