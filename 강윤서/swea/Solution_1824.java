package 강윤서.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1824 {
	
	static int R, C;
	static char[][] map;
	static boolean[][][][] visited;
	static int mem;
	static boolean flag;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			flag = false;
			mem = 0;
			map = new char[R][C];
			visited = new boolean[R][C][16][4];
			for (int i = 0; i < R; i++) {
				String input = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '@') flag = true;
				}
			}
			if (flag) {
				System.out.println("#" + t + " " + (dfs(0, 0, mem, 3)?"YES":"NO"));
			} else {
				System.out.println("#" + t + " NO");
			}
		}
	}
	
	private static boolean dfs(int r, int c, int mem, int dir) {
		if(r<0) {
			r=R-1;
		}else if(r>=R) {
			r=0;
		}else if(c<0) {
			c=C-1;
		}else if(c>=C) {
			c=0;
		}
		if (visited[r][c][mem][dir]) return false; // 순환
		visited[r][c][mem][dir] = true;
		switch(map[r][c]) {
			case '<':
				dir=2;
				break;
			case '>':
				dir=3;
				break;
			case '^':
				dir=0;
				break;
			case 'v':
				dir=1;
				break;
			case '_':
				if(mem==0) dir=3;
				else dir=2;
				break;
			case '|':
				if(mem==0) dir=1;
				else dir=0;
				break;
			case '?':
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (dfs(nr, nc, mem, i)) return true;
				}
				return false;
			case '.':
				break;
			case '@':
				return true;
			case '+':
				mem=(mem+1)%16;
				break;
			case '-':
				mem=(mem==0)?15:mem-1;
				break;
			case '0':
				mem = 0;
				break;
			case '1':
				mem = 1;
				break;
			case '2':
				mem = 2;
				break;
			case '3':
				mem = 3;
				break;
			case '4':
				mem = 4;
				break;
			case '5':
				mem = 5;
				break;
			case '6':
				mem = 6;
				break;
			case '7':
				mem = 7;
				break;
			case '8':
				mem = 8;
				break;
			case '9':
				mem = 9;
				break;
		}
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		return dfs(nr, nc, mem, dir);
	}
}
