import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


class Player{
	int x;
	int y;
	int keyA;
	int keyB;
	int keyC;
	int keyD;
	int keyE;
	int keyF;
	int cnt;
	public Player(int x, int y, int keyA, int keyB, int keyC, int keyD, int keyE, int keyF, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.keyA = keyA;
		this.keyB = keyB;
		this.keyC = keyC;
		this.keyD = keyD;
		this.keyE = keyE;
		this.keyF = keyF;
		this.cnt = cnt;
	}
	
	
}

public class Main2 {

	static int answer;
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	static char[][] map;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int startX = 0;
		int startY = 0;
		
		map = new char[N+2][M+2];
		
		// 테두리 벽으로 채우기
		Arrays.fill(map[0], '#');
		Arrays.fill(map[N+1], '#');
		for (int i = 1; i < N+1; i++) {
			String input = br.readLine();
			for (int j = 1; j < M+1; j++) {
				map[i][j]=input.charAt(j-1);
				if(map[i][j]=='0') { // 스타트 지점 좌표 얻기
					startX=i;
					startY=j;
				}
			}
			map[i][0]='#';
			map[i][M+1]='#';
		}
		
		answer = bfs(startX, startY);
		
		System.out.println(answer);
	}
	
	private static int bfs(int x, int y) {
		// x, y, keyA유무, keyB유무, keyC유무, keyD유무, keyE유무, keyF유무 방문체크배열
		boolean[][][][][][][][] visited = new boolean[N+2][M+2][2][2][2][2][2][2];
		Deque<Player> dq = new ArrayDeque<>(); 
		// 초기값 넣기
		dq.offerLast(new Player(x, y, 0, 0, 0, 0, 0, 0, 0));
		// 초기값 true로 만들어주기
		visited[x][y][0][0][0][0][0][0] = true;
		while(!dq.isEmpty()) {
			Player p = dq.poll();
			if(map[p.x][p.y]=='1') { // 1을 찾으면 정답 리턴
				return p.cnt;
			}
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(map[nx][ny]!='#') { // 벽이 아닌경우, 테두리를 채워줬으므로 테두리 조건이 이걸로 대체된다.
					switch(map[nx][ny]) {
						case 'a': // a 열쇠가 있을때니까 keyA 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][1][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF]) {
								visited[nx][ny][1][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, 1, p.keyB, p.keyC, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'b': // b 열쇠가 있을때니까 keyB 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][p.keyA][1][p.keyC][p.keyD][p.keyE][p.keyF]) {
								visited[nx][ny][p.keyA][1][p.keyC][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, 1, p.keyC, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'c': // c 열쇠가 있을때니까 keyC 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][p.keyA][p.keyB][1][p.keyD][p.keyE][p.keyF]) {
								visited[nx][ny][p.keyA][p.keyB][1][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, 1, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'd': // d 열쇠가 있을때니까 keyD 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][1][p.keyE][p.keyF]) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][1][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, 1, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'e': // e 열쇠가 있을때니까 keyE 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][1][p.keyF]) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][1][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, p.keyD, 1, p.keyF, p.cnt+1));
							}
							break;
						case 'f': // f 열쇠가 있을때니까 keyF 부분은 무조건 1로 만들어준다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][1]) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][1]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, p.keyD, p.keyE, 1, p.cnt+1));
							}
							break;
						case 'A': // a 열쇠가 있을때니까 keyA 부분은 무조건 1이어야 하고 더불어 keyA를 소지한 상태여야 한다.
							if(!visited[nx][ny][1][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF] && p.keyA==1) {
								visited[nx][ny][1][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, 1, p.keyB, p.keyC, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'B': // b 열쇠가 있을때니까 keyB 부분은 무조건 1이어야 하고 더불어 keyB를 소지한 상태여야 한다.
							if(!visited[nx][ny][p.keyA][1][p.keyC][p.keyD][p.keyE][p.keyF] && p.keyB==1) {
								visited[nx][ny][p.keyA][1][p.keyC][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, 1, p.keyC, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'C': // c 열쇠가 있을때니까 keyC 부분은 무조건 1이어야 하고 더불어 keyC를 소지한 상태여야 한다.
							if(!visited[nx][ny][p.keyA][p.keyB][1][p.keyD][p.keyE][p.keyF] && p.keyC==1) {
								visited[nx][ny][p.keyA][p.keyB][1][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, 1, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'D': // d 열쇠가 있을때니까 keyD 부분은 무조건 1이어야 하고 더불어 keyD를 소지한 상태여야 한다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][1][p.keyE][p.keyF] && p.keyD==1) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][1][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, 1, p.keyE, p.keyF, p.cnt+1));
							}
							break;
						case 'E': // e 열쇠가 있을때니까 keyE 부분은 무조건 1이어야 하고 더불어 keyE를 소지한 상태여야 한다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][1][p.keyF] && p.keyE==1) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][1][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, p.keyD, 1, p.keyF, p.cnt+1));
							}
							break;
						case 'F': // f 열쇠가 있을때니까 keyF 부분은 무조건 1이어야 하고 더불어 keyF를 소지한 상태여야 한다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][1] && p.keyF==1) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][1]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, p.keyD, p.keyE, 1, p.cnt+1));
							}
							break;
						default: // 1이거나 .이거나 방문체크하고 넣어주면 된다.
							if(!visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF]) {
								visited[nx][ny][p.keyA][p.keyB][p.keyC][p.keyD][p.keyE][p.keyF]=true;
								dq.offerLast(new Player(nx, ny, p.keyA, p.keyB, p.keyC, p.keyD, p.keyE, p.keyF, p.cnt+1));
							}
					}
				}
			}
		}
		// 아무것도 못찾았으면 -1 리턴
		return -1;
	}
}
