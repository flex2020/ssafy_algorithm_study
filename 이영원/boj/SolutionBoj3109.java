import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	// 오른위, 오른쪽, 오른아래
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	
	static char[][] map;
	static boolean[][] visited;
	static int R;
	static int C;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R+2][C+2];
		visited = new boolean[R+2][C+2];
		answer=0;
		
		Arrays.fill(map[0], '?');
		Arrays.fill(map[R+1], '?');
		for (int i = 1; i < R+1; i++) { // 입력
			String input = br.readLine();
			for (int j = 1; j < C+1; j++) {
				map[i][j] = input.charAt(j-1);
				if(map[i][j]=='x') visited[i][j]=true;
			}
			map[i][0] = '?';
			map[i][C+1] = '?';
		}
		for(int i=1;i<R+1;i++) {
			dfs(i, 0);
		}
		
		System.out.println(answer);
	}
	
	private static boolean dfs(int x, int y) {
		// basis part
		if(y==C) {
			answer++;
			return true;
		}
		
		// inductive part
		for(int i=0;i<3;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(map[nx][ny]=='.' && !visited[nx][ny]) {
				visited[nx][ny]=true;
				if(dfs(nx, ny)) return true;
			}
		}
		return false;
	}

}
