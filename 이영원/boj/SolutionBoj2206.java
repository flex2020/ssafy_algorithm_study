import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int puk;
	int dist;
	Point(int x, int y, int puk, int dist){
		this.x = x;
		this.y = y;
		this.puk = puk;
		this.dist = dist;
	}
}

// 까먹을때쯤 다시한번 풀어보기
public class Main {
	
	// 우 하 좌 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] map;
	static int answer;																																																																																																																				
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer=1;
		
		map = new int[N+2][M+2];
//		visited = new boolean[N+2][M+2];
		
		Arrays.fill(map[0], -1);
		Arrays.fill(map[N+1], -1);
		for(int i=1;i<N+1;i++) {
			char[] input = br.readLine().toCharArray();
			for(int j=1;j<M+1;j++) {
				map[i][j] = input[j-1]-'0';
			}
			map[i][0] = -1;
			map[i][M+1] = -1;
		}
		
		answer = bfs();
		
		System.out.println(answer);
	}
	
	private static int bfs() {
		// visited는 벽을 부순적이 있으면 visited[x][y][1]에 방문체크, 
		// 벽을 부순적이 없으면 visited[x][y][0]에 방문체크를 하는 것이다.
		boolean[][][] visited = new boolean[N+2][M+2][2];
		
		Deque<Point> dq = new ArrayDeque<>();
		dq.add(new Point(1,1,0,1));
		visited[1][1][0]=true;
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
//			System.out.println(p.x + " " + p.y + " " + p.puk + " " + p.dist);
			if(p.x==N && p.y==M) return p.dist;
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 벽이 없으면 그냥 가기
				if(map[nx][ny]==0 && !visited[nx][ny][p.puk]) {
					visited[nx][ny][p.puk]=true;
					dq.offerLast(new Point(nx, ny, p.puk, p.dist+1));
				}
				// 벽이 있으면 부수고 가기
				else if(map[nx][ny]==1 && p.puk==0) {
					visited[nx][ny][1]=true;
					dq.offerLast(new Point(nx, ny, 1, p.dist+1));
				}
			}
		}
		return -1;
	}
}
