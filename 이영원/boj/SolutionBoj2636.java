import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
	static boolean[][] visited;
	static int N;
	static int M;
	static int[][] numMap;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; // 입력받은 맵
		numMap = new int[N][M]; // 넘버링 맵
		visited = new boolean[N][M];
		int prevData=0; // 이전 데이터
		int time=0; // 시간
		boolean flag=false; // 탈출용 플래그
		int num=1; // 넘버링 숫자
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				if(map[i][j]==1) map[i][j]=2;
			}
		}
		
		for (int i = 0; i < N; i++) { // 초기 세팅(numMap 넘버링)
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j]==0) {
					bfs(i, j, num++);
				}
			}
		}
		
		int minX=1;
		int minY=1;
		int maxX=map.length-1;
		int maxY=map[0].length-1;
		
		do {
			flag=false;
			int pd=0;
			// 지울거 마킹
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if(map[i][j]==1) {
						for(int d=0;d<4;d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if(numMap[nx][ny]==1) { // 바람이 통하면 없어져야한다.
								map[i][j]=-1;
								pd++; // prevData에 넣을 값
								flag=true; // 하나라도 있으면 true로 변환
//								Thread.sleep(500);
//								print(map);
								break;
							}
						}
					}
				}
			}
			// 마킹한거 지우기
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if(map[i][j]==-1) {
						map[i][j]=0; // map은 치즈가 녹았으므로 0으로 변경
						numMap[i][j]=1; // numMap은 가장 바깥인 1로 변경
//						Thread.sleep(500);
//						print(map);
					}
				}
			}
			// numMap에서 가장 바깥인 1과 연결된 0이 있는지 확인하기 위해 bfs 진행
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < M-1; j++) {
					if(numMap[i][j]==1) { // 1과 nx, ny가 닿아있는 경우
						for(int d=0;d<4;d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if(nx>=0 && nx<N && ny>=0 && ny<M && numMap[nx][ny]>1) { // numMap이 1보다 크면 그것이므로 bfs 진행
								visited = new boolean[N][M];
								bfs(nx, ny, 1); // 1로 초기화
							}
						}
					}
				}
			}
			if(pd!=0) prevData = pd; // 0이면 필요없으므로 0이 아닌 이전 데이터를 prevData에 넣는다.
			time++; // 시간을 더해준다.
		} while(flag);
		System.out.println(time-1); // 다 됐을 때 한번더 시행하므로 -1을 해준다.
		System.out.println(prevData);

	}
	
	// bfs를 이용해 0 부분을 넘버링 한다.
	private static void bfs(int x, int y, int num) {
		Deque<Point> dq = new ArrayDeque<>();
		dq.offerLast(new Point(x, y));
		visited[x][y]=true;
		numMap[x][y]=num;
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(map[nx][ny]==0 && !visited[nx][ny]) {
						visited[nx][ny]=true;
						numMap[nx][ny]=num;
						dq.offerLast(new Point(nx, ny));
					}
				}
			}
		}
//		print(numMap);
	}
	
	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
