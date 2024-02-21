import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main2 {
	
	static int N;
	static int M;
	static int zeroCnt; // 0의 개수 (초기값)
	
	static int[][] map; // 맵
	static List<Point> CCTVs; // CCTV 좌표 리스트로 저장
	static boolean[][] visited; // 방문체크배열
	static int answer; // 정답
	
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
	static int[][] dir = { // CCTV가 감시하는 방향을 dx, dy의 인덱스로 저장(2차원배열)
			{},
			{0},
			{0, 2},
			{0, 1},
			{0, 1, 2},
			{0, 1, 2, 3}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		zeroCnt = 0;
		
		map = new int[N+2][M+2]; // 테두리 벽으로 채우기
		CCTVs = new ArrayList<>();
		visited = new boolean[N+2][M+2];
		answer = Integer.MAX_VALUE;
		zeroCnt=0;
		
		Arrays.fill(map[0], 6);
		Arrays.fill(map[N+1], 6);
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < M+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) zeroCnt++; 
				else if(map[i][j]>=0 && map[i][j]<=5) CCTVs.add(new Point(i, j));
			}
			map[i][0]=6;
			map[i][M+1]=6;
		}
		
		setPoint(0, 0);
		
		System.out.println(answer);
	}
	
	// 포인트 설정 - result = 현재까지 감시한 장소 수, cnt = CCTVs 리스트의 포인트의 인덱스
	private static void setPoint(int result, int cnt) throws InterruptedException {
		// basis part
		// cnt와 CCTVs의 사이즈가 같다면 어느 방향으로든 한번은 다 봤다는 뜻으로 answer의 최솟값을 찾고 넣는다.
		if(cnt==CCTVs.size()) {
			answer = Math.min(answer, zeroCnt-result);
//			System.out.println(answer);
			return;
		}
//		if(answer!=Integer.MAX_VALUE && zeroCnt-result<=answer) return;
		// inductive part
		Point p = CCTVs.get(cnt); // cnt에 해당하는 포인트를 넣는다.
		// 1,3,4는 4번의 반복, 2는 2번의 반복, 5는 한번의 반복을 하면서 가중치를 setDir 메소드에 보내도록 한다.
		for (int i = 0; i < 4; i++) {
			setDir(i, p, result, 0, cnt);
			if(map[p.x][p.y]==2 && i==1) break;
			else if(map[p.x][p.y]==5) i=4;
		}
	}
	
	// 방향대로 반복
	// weight = 가중치, p = CCTV 좌표값, result = 현재까지 감시한 장소 수, idx = dir 2차원 배열의 몇번째 인덱스를 가져올건지
	// cnt = CCTVs 리스트의 현재 포인트의 인덱스
	private static void setDir(int weight, Point p, int result, int idx, int cnt) throws InterruptedException {
		// basis part
//		if(answer!=Integer.MAX_VALUE && zeroCnt-result<=answer) return; -> 이거 왜 안댐?
		// inductive part
		// 가중치를 기반으로 방향을 설정하여 넣고 move 메소드에 보내준다.
		int dirX = dx[(dir[map[p.x][p.y]][idx]+weight)%4];
		int dirY = dy[(dir[map[p.x][p.y]][idx]+weight)%4];
		move(p, new Point(dirX, dirY), result, weight, idx, dir[map[p.x][p.y]].length, cnt);
//		for (int i = 0; i < dir[map[p.x][p.y]].length; i++) {
//
//		}
	}
	
	// 실제로 벽을 만날때까지 반복적으로 이동하면서 방문체크하는 메소드
	// p = 현재 포인트, moveP = 이동시킬 값, result = 현재까지 감시한 장소 수, weight = 가중치, idx = dir 2차원 배열의 몇번째
	// 인덱스를 가져올건지, len = 해당 dir 2차원배열의 길이, cnt = CCTVs 리스트의 현재 포인트의 인덱스
	private static boolean move(Point p, Point moveP, int result, int weight, int idx, int len, int cnt) throws InterruptedException {
		// basis part
//		Thread.sleep(500);
//		print();
		// 벽을 만났다면 멈추기
		if(map[p.x][p.y]==6) {
			if(idx != len-1) { // idx != len-1이라면 모든 방향을 돌아본 것이 아니므로 setDir에 idx를 하나 올려서 보낸다.
				// 예를 들어, 왼쪽, 위쪽, 오른쪽을 다 체크해야하는데 오른쪽만 체크한 상태면 다음을 체크하도록 하는 것이다.
				setDir(weight, CCTVs.get(cnt), result, idx+1, cnt);
			}else { // 정해진 방향을 모두 살펴봤다면 다음 CCTV 포인트에서 다시 방문체크를 할 수 있도록 하는 것이다.
				setPoint(result, cnt+1);
			}
			return true;
		}
		// inductive part 해당 방향만큼 move 메소드를 호출
		for (int i = 0; i < dir.length; i++) {
			int nx = p.x + moveP.x;
			int ny = p.y + moveP.y;
			if(!visited[nx][ny] && map[nx][ny]==0) { // 방문되지 않고 0인 경우
				visited[nx][ny]=true; // 방문체크
//				System.out.println(nx + " " + ny);
				if(move(new Point(nx, ny), moveP, result+1, weight, idx, len, cnt)) {
					// 위 setDIr, setPoint에서 true를 리턴하면 그전까지의 다시 setDir, setPoint하기전까지의 move는 모두
					// 의미없기 때문에 방문체크를 해제하고 true를 setDir, setPoint를 만날때까지 반복적으로 리턴한다.
					visited[nx][ny]=false;
					return true;
				}
			}else { // 방문되거나 CCTV거나 벽인 경우, result를 올리지 않고 넘긴다.
				// 위와 동일
				if(move(new Point(nx, ny), moveP, result, weight, idx, len, cnt)) return true;
			}
		}
		// 여기까지 오면 그냥 틀린겨
		return false;
	}
	
	// 테스트용 프린트함수
	private static void print() {
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				System.out.print((visited[i][j]?1:0) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
