import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {1, 0, 1};
	static int[] dy = {1, 1, 0};
	
	static int[][] map;
	static int N;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		answer = 0;
		
		map = new int[N+2][N+2];
		
		Arrays.fill(map[0], 1);
		Arrays.fill(map[N+1], 1);
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			map[i][0] = 1;
			map[i][N+1] = 1;
		}
		
		dfs(1, 2, 0);
		System.out.println(answer);
		
	}
	
	// type(0:가로, 1:세로, 2:대각), 끝점만 비교
	private static void dfs(int x, int y, int type) {
//		System.out.println(x + " " + y);
		// basis part
		if(x==N && y==N) {
			answer++;
			return;
		}
		// inductive part
		switch(type) {
			case 0:
				if(checkGaro(x, y)) {
					dfs(x, y+1, type);
				}
				if(checkDaegak(x, y)) {
					dfs(x+1, y+1, type+2);
				}
				break;
			case 1:
				if(checkSero(x, y)) {
					dfs(x+1, y, type);
				}
				if(checkDaegak(x, y)) {
					dfs(x+1, y+1, type+1);
				}
				break;
			case 2:
				if(checkGaro(x, y)) {
					dfs(x, y+1, type-2);
				}
				if(checkSero(x, y)) {
					dfs(x+1, y, type-1);
				}
				if(checkDaegak(x, y)) {
					dfs(x+1, y+1, type);
				}
				break;
		}
	}
	
	private static boolean checkGaro(int x, int y) {
		int nx = x + dx[1];
		int ny = y + dy[1];
		if(map[nx][ny]==1) return false;
		return true;
	}
	
	private static boolean checkSero(int x, int y) {
		int nx = x + dx[2];
		int ny = y + dy[2];
		if(map[nx][ny]==1) return false;
		return true;
	}
	
	private static boolean checkDaegak(int x, int y) {
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
//			if(x==1 && y==3) System.out.println(nx + " " + ny);
			if(map[nx][ny]==1) return false;
		}
		return true;
	}
	
}
