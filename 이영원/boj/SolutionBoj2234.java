import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
	
	static int N, M, maxCnt; // 가장큰놈
	static int[][] map; // 맵
	static int[][] numberingMap; // 넘버링한 맵
	static boolean[][] visited; // 방문체크배열
	static List<Integer> cnts; // 각 numbering한게 몇개인지 기록한 리스트
	static int[] dx = {0, -1, 0, 1}; // 좌 상 우 하
	static int[] dy = {-1, 0, 1, 0}; // 좌 상 우 하
	static List<Set<Integer>> setList; // 인접하는 놈 저장한 setList
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 둘이 헷갈려서 바꿈
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		maxCnt = 0;
		
		map = new int[N][M];
		numberingMap = new int[N][M];
		visited = new boolean[N][M];
		setList = new ArrayList<>();
		setList.add(new HashSet<>());
		cnts = new ArrayList<>();
		cnts.add(0);
		
		int num=1;
		int answer=0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(numberingMap[i][j]==0) {
					cnts.add(bfs(num++, i, j));
//					print(numberingMap);
				}
			}
		}
		
		sb.append(num-1).append("\n");
		sb.append(maxCnt).append("\n");
		
		// 반복문 돌면서 벽 하나 뚫었을 때의 최대치 구하기
		for (int i = 1; i < setList.size(); i++) {
			for(int j : setList.get(i)) {
				answer = Math.max(answer, cnts.get(i) + cnts.get(j));
			}
		}
		
		sb.append(answer);
		
		System.out.println(sb);
		
	}
	
	// bfs돌면서 넘버링
	private static int bfs(int num, int x, int y) {
		int cnt=0; // 해당 number가 몇개인지 카운트
		setList.add(new HashSet<>());
		Deque<Point> dq = new ArrayDeque<>();
		dq.offerLast(new Point(x, y));
		visited[x][y] = true;
		numberingMap[x][y]=num;
		cnt++;
		while(!dq.isEmpty()) {
			
			Point p = dq.pollFirst();
			
			for (int i = 0; i < 4; i++) {
				
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				else if((map[p.x][p.y] & (1 << i)) != 0) { // 좌 상 우 하 에 벽이있는 경우
					// 벽 건너편이 numbering된 다른 놈인 경우, setList에 추가(현재 number와 인접한다는 뜻)
					if(numberingMap[nx][ny]!=num && numberingMap[nx][ny]!=0) {
						setList.get(num).add(numberingMap[nx][ny]);
					}
					continue;
				}
				if(visited[nx][ny]) continue;
				
				visited[nx][ny]=true;
				cnt++;
				numberingMap[nx][ny]=num;
				dq.offerLast(new Point(nx, ny));

			}
			
		}
		
		// 가장큰놈 넣기
		maxCnt = Math.max(maxCnt, cnt);
		
		return cnt;
	}
	
	private static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
