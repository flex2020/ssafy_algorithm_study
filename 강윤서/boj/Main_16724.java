package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_16724 {
	static int N, M, answer;
	static int[][] board;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new int[N][M];
		for (int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				int dir = -1;
				if (input[j] == 'U') dir = 0;
				else if (input[j] == 'D') dir = 1;
				else if (input[j] == 'L') dir = 2;
				else dir = 3;
				board[i][j] = dir;
				visited[i][j] = -1;
			}
		}
		int cnt = 1;
		int answer = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (visited[i][j] == -1) {
					visited[i][j] = cnt;
					int value = dfs(i, j);
					if (cnt == value) cnt++;
					visited[i][j] = value;
				}
			}
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				answer = Math.max(answer, visited[i][j]);
			}
		}
		System.out.println(answer);
	}
	public static int dfs(int r, int c) {
		int dir = board[r][c];
		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if (visited[nr][nc] != -1) return visited[nr][nc];
		else {
			visited[nr][nc] = visited[r][c];
			visited[nr][nc] = dfs(nr, nc);
		}
		return visited[nr][nc];
	}
	public static void print() {
		System.out.println("===================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
}
