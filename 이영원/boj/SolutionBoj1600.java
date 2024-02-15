import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int cnt;
	int kCnt;
	Point(int x, int y, int cnt, int kCnt){
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.kCnt = kCnt;
	}
}

public class Main {
	
	static int K; // 말 움직임
	static int W; // 가로길이
	static int H; // 세로길이
	static int answer;
	static int[][] map;
	
	// 7까지 말 이동, 8 9 10 11은 원숭이 이동
	static int[] dx = {1,2,2,1,-1,-2,-2,-1,0,1,0,-1};
	static int[] dy = {2,1,-1,-2,-2,-1,1,2,1,0,-1,0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
			
		map = new int[H+1][W+1];
//		visited = new boolean[N+2][M+2];
		
		for (int i = 1; i < H+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < W+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = bfs();
		
		System.out.println(answer);
	}
	
	private static int bfs() {
		boolean[][][] visited = new boolean[H+1][W+1][K+1];
		
		Deque<Point> dq = new ArrayDeque<>();
		visited[1][1][K]=true;
		dq.offerLast(new Point(1,1,0,K));
		
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
//			System.out.println(p.x + " " + p.y + " " + p.cnt + " " + p.kCnt);
			if(p.x==H && p.y==W) return p.cnt;
			for(int i=0;i<12;i++) {
				if(i<=7 && p.kCnt==0) continue;
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx>=1 && nx<=H && ny>=1 && ny<=W && map[nx][ny]==0) {
					// 말 이동인 경우
					if(i<=7) {
						if(!visited[nx][ny][p.kCnt-1] && p.kCnt!=0) {
							visited[nx][ny][p.kCnt-1]=true;
							dq.offerLast(new Point(nx, ny, p.cnt+1, p.kCnt-1));
						}
					}else { // 원숭이 이동인 경우
						if(!visited[nx][ny][p.kCnt]) {
							visited[nx][ny][p.kCnt]=true;
							dq.offerLast(new Point(nx, ny, p.cnt+1, p.kCnt));
						}
					}
				}
			}
		}
		return -1;
	}
		
}
