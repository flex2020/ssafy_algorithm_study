import java.io.*;
import java.util.*;
class CCTV {
	int r, c, type;
	CCTV (int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
	}
}
public class Main_15683 {
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] board, visited;
	static List<int[][]> direction;
	static List<CCTV> cctvList;
	static int[] dr = {-1 ,1, 0, 0}, dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M]; // -1: 탐색가능
		visited = new int[N][M]; // 방문 배열을 int로 -> 해당 위치에 겹치는 수를 구하기 위해
		cctvList = new ArrayList<>();
		direction = new ArrayList<>();
		direction.add(new int[1][1]);
		direction.add(new int[][] {{0}, {1}, {2}, {3}}); // 1번
		direction.add(new int[][] {{0, 1}, {2, 3}}); // 2번
		direction.add(new int[][] {{0, 2}, {0, 3}, {1, 2}, {1, 3}}); // 3번
		direction.add(new int[][] {{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2}}); // 4번
		direction.add(new int[][] {{0, 1, 2, 3}}); // 5번

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				 board[i][j] = Integer.parseInt(st.nextToken());
				 if (board[i][j] == 6) visited[i][j] = -1;
				 if (board[i][j] >= 1 && board[i][j] <= 5) { // CCTV
					 visited[i][j] = -1;
					 cctvList.add(new CCTV(i, j, board[i][j]));
				 }
			}
		}
		dfs(0);
		System.out.println(answer);
	}
	public static void dfs(int cnt) {
		if (cnt == cctvList.size()) {
			// 방향을 결정한 CCTV 개수가 총 CCTV 개수와 동일할 때
			int result = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (visited[i][j] == 0) { // 사각지대
						result++;
					}
				}
			}
			answer = Math.min(answer, result);
			return ;
		}
		int r = cctvList.get(cnt).r;
		int c = cctvList.get(cnt).c;
		int type = board[r][c];
		
		for (int i=0; i<direction.get(type).length; i++) { // cctv 종류에 따라 감시 방향 다름
			// CCTV의 방향대로 board 탐색해보기
			for (int idx=0; idx<direction.get(type)[i].length; idx++) {
				for (int length=1; length<=Math.max(N, M); length++) {
					int nr = r + dr[direction.get(type)[i][idx]] * length;
					int nc = c + dc[direction.get(type)[i][idx]] * length;
					if (0<=nr && nr<N && 0<=nc && nc<M) {
						if (board[nr][nc] == 6) break ;
						if (board[nr][nc] == 0) visited[nr][nc]++;
					}
				}
				
			}
			dfs(cnt+1);
			// 탐색했던거 원상태로
			for (int idx=0; idx<direction.get(type)[i].length; idx++) {
				for (int length=1; length<Math.max(N, M); length++) {
					int nr = r + dr[direction.get(type)[i][idx]] * length;
					int nc = c + dc[direction.get(type)[i][idx]] * length;
					if (0<=nr && nr<N && 0<=nc && nc<M) {
						if (board[nr][nc] == 6) break;
						if (visited[nr][nc] > 0) visited[nr][nc]--;
					}
				}
			}
		}
	}
}
