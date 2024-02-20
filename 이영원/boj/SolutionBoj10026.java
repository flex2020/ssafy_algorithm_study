import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
	static int N;
	static boolean[][] check;
	static char[][] map;
	static int answer1;
	static int answer2;
	
	static class Point{
		int x;
		int y;
		char color;
		
		public Point(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		check = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=input.charAt(j);
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!check[i][j]) {
					bfs(i, j, map[i][j], 1); // 그냥
				}
			}
		}
		
		check = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!check[i][j]) {
					bfs(i, j, map[i][j], 2); // 색맹
				}
			}
		}
		
		System.out.println(answer1 + " " + answer2);
		
	}
	
	private static void bfs(int x, int y, char color, int type) {
		if(type==1) answer1++; // 그냥이면 answer1에 담기
		else answer2++; // 색맹이면 answer2에 담기
		Deque<Point> dq = new ArrayDeque<>();
		dq.offerLast(new Point(x, y, color));
		check[x][y]=true;
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N && !check[nx][ny]) {
					if(type==1) { // 그냥이면 색깔비교만 하면 된다.
						if(map[nx][ny]==color) {
							check[nx][ny]=true;
							dq.offerLast(new Point(nx, ny, color));
						}
					}else { // 색맹이면 R, G를 같은걸로 판단하고 비교하면 된다.
						if((color=='R' || color=='G') && (map[nx][ny]=='R' || map[nx][ny]=='G')) {
							check[nx][ny]=true;
							dq.offerLast(new Point(nx, ny, color));
						}else if(map[nx][ny]==color) {
							check[nx][ny]=true;
							dq.offerLast(new Point(nx, ny, color));
						}
					}

				}
			}
		}
		
	}
	
}
