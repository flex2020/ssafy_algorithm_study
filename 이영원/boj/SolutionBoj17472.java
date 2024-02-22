import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int[][] numberingMap; // 넘버링 맵
	static int[][] map; // 입력받은 맵
	static boolean[][] visited; // 맵 넘버링할 때 사용한 bfs 체크배열
	static int[][] bridgeMtx; // 인접행렬
	static boolean[] visitedpq; // pq 사용시 사용한 체크배열
	
	static class Edge{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int num=2; // 넘버링은 2부터
		int answer=-1;
		
		// 테두리 처리
		map = new int[N+2][M+2];
		numberingMap = new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		
		
		Arrays.fill(map[0], -1);
		Arrays.fill(map[N+1], -1);
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<M+1;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) numberingMap[i][j]=1; // 섬이면 일단 1로 초기화
			}
			map[i][0] = -1;
			map[i][M+1] = -1;
		}
		
		// 지도 넘버링
		for (int i = 1; i < N+1; i++) {
			for(int j=1;j<M+1;j++) {
				if(map[i][j]!=0 && numberingMap[i][j]==1) {
					bfs(i, j, num++);
				}
			}
		}
//		print(numberingMap);
		
		bridgeMtx = new int[num][num];
		// 인접행렬 초기화(num이 2부터니까 2부터 초기화)
		for(int i=2;i<num;i++) {
			for(int j=2;j<num;j++) {
				bridgeMtx[i][j] = 9; // 9 이상으로는 안나오니까 print하게 이쁘려고 9로 했다.
			}
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(map[i][j]==1 && map[nx][ny]==0) { // 현재 내가 섬이고 다음이 바다면 진행
//						print(bridgeMtx);
//						System.out.println(i + " " + j + " "  + d + " " + numberingMap[i][j]);
						straight(i, j, d, numberingMap[i][j]);
					}
				}
			}
		}
		
//		print(bridgeMtx);
		
		visitedpq = new boolean[num];
		answer = prim();
		for (int i = 2; i < visitedpq.length; i++) {
			if(!visitedpq[i]) answer=-1;
		}
		System.out.println(answer);
	}
	
	private static int prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> {
			return a.weight - b.weight;
		});
		Deque<Integer> dq = new ArrayDeque<>();
		int answer=0;
		
		dq.offerLast(2); // 시작나라는 2로 설정
		while(!dq.isEmpty()) {
			// 시작 정점 및 연결된 정점 방문처리
			int current = dq.poll();
			visitedpq[current] = true;
			
			for (int i = 2; i < bridgeMtx[current].length; i++) {
				if(!visitedpq[i] && bridgeMtx[current][i]!=9) {
//					System.out.println(Arrays.toString(visitedpq));
//					System.out.println(bridgeMtx[current].length);
					pq.add(new Edge(current, i, bridgeMtx[current][i]));
				}
			}
			
			// 이미 방문한 것들 중 가장 작은 가중치가 나올 경우 한번 더 빼서 또 확인
			while(!pq.isEmpty()) {
				Edge e = pq.poll(); // pq에서 뽑았으므로 현재 정점에서 방문하지 않은 정점들 중 가장 작은 가중치를 가진게 나온다.
				if(!visitedpq[e.to]) {
//					if(answer==-1) answer=0;
					dq.offerLast(e.to);
					visitedpq[e.to]=true;
					answer+=e.weight;
					break; // break 한다고 나온게 아니다. pq에는 한번 체크한 것들이 남아있다.
				}
			}
		}
		
		return answer;
	}
	
	// 만날때까지 진행(x, y 좌표, 진행방향, 나라이름)
	private static void straight(int x, int y, int dir, int countryNum1) {
		int len = 0;
		int nx = x;
		int ny = y;
		int countryNum2=0;
		while(map[nx][ny]!=-1) { // 맵 끝에 만날때까지 진행(먼저 만족하면 break)
			nx += dx[dir];
			ny += dy[dir];
			if(map[nx][ny]==1) { // 1이면 일단 나라를 만났다.
				// 길이가 1이거나 같은 나라를 만난경우 리턴
				if(len<2 || numberingMap[nx][ny]==numberingMap[x][y]) return;
//				System.out.println(x + " " + y + " " + nx + " " + ny + " " + numberingMap[x][y] + " " + numberingMap[nx][ny]);
				countryNum2=numberingMap[nx][ny]; // 아니면 만난 나라 받기
				break;
			}else if(map[nx][ny]==0) { // 바다면 다리길이 늘리기
				len++;
			}
		}
		
		// 인접행렬에 최솟값 넣기
		bridgeMtx[countryNum1][countryNum2]=Math.min(bridgeMtx[countryNum1][countryNum2], len);
		bridgeMtx[countryNum2][countryNum1]=bridgeMtx[countryNum1][countryNum2];
	}
	
	private static void bfs(int x, int y, int num) {
		Deque<Point> dq = new ArrayDeque<>();
		dq.offerLast(new Point(x, y));
		numberingMap[x][y]=num;
		visited[x][y]=true;
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 나라인데 방문 안했고, 아직 다른 나라로 정해지지 않은 경우
				if(map[nx][ny]==1 && !visited[nx][ny] && numberingMap[nx][ny]==1) {
					numberingMap[nx][ny]=num; // 나라 숫자로 넣기
					visited[nx][ny]=true;
					dq.offerLast(new Point(nx, ny));
				}
			}
		}
	}
	
	private static void print(int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
