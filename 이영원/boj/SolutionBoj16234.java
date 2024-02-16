import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Point;

class Country{
	int cnt; // 동맹인 나라 수
	int sum; // 동맹 나라 인구수 합
	List<Point> allies; // 동맹 나라 좌표 리스트
	
	public Country(int cnt, int sum, List<Point> allies) {
		super();
		this.cnt = cnt;
		this.sum = sum;
		this.allies = allies;
	}

	@Override
	public String toString() {
		return "Country [cnt=" + cnt + ", sum=" + sum + ", allies=" + allies + "]";
	}
	
}

public class Main {
	
	static int[][] tmpMap; // 인구수가 바뀌는 맵
	static int[][] map; // 오리지널 맵
	static int answer;
	static int N, L, R;
	static Country[][] countries;
	static int[] dx = {0,1,0,-1}; // 우 하 좌 상
	static int[] dy = {1,0,-1,0}; // 우 하 좌 상

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 반복문 탈출용 플래그
		boolean flag = false;
		
		map = new int[N][N];
		tmpMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				tmpMap[i][j]=map[i][j];
			}
		}
		
		while(true) {
			// List, 방문배열, 플래그 초기화
			countries = new Country[N][N];
			boolean[][] visited = new boolean[N][N];
			flag=false;
			
			// bfs 돌리면서 넘버링 진행
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					countries[i][j] = bfs(i, j, visited);
				}
			}
			
			// 넘버링한 것들에게 인구 재분배 진행
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
//					System.out.println(countries[i][j]);
					if(countries[i][j].allies.size()!=1) {
						flag=true;
						int newPeople = countries[i][j].sum/countries[i][j].cnt;
						for(Point p : countries[i][j].allies) {
							tmpMap[p.x][p.y] = newPeople;
						}
					}
				}
			}
			
			// List size가 0이면 아무것도 없다는 뜻으로 반복문 탈출
			if(!flag) break;
			
			answer++;
			
		}
		
		System.out.println(answer);
	}
	
	private static Country bfs(int x, int y, boolean[][] visited) {
		// 자기 자신은 동맹이니까 새로운 Country 객체 생성
		Country c = new Country(1, tmpMap[x][y], new ArrayList<>());
		c.allies.add(new Point(x, y));
		// 이미 방문된 상황인 경우 더 할 필요없으므로 객체만 생성하고 리턴
		if(visited[x][y]) return c;
		
		Deque<Point> dq = new ArrayDeque<>();
		visited[x][y]=true;
		dq.offerLast(new Point(x, y));
		
		while(!dq.isEmpty()) {
			Point p = dq.pollFirst();
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<N && nx>=0 && ny<N && ny>=0 && !visited[nx][ny]) {
					// L 이상 R 이하인지 확인하고 들어가기
					if(Math.abs(tmpMap[p.x][p.y]-tmpMap[nx][ny]) >= L && Math.abs(tmpMap[p.x][p.y]-tmpMap[nx][ny]) <= R) {
						visited[nx][ny]=true;
						// allies 리스트에 해당 좌표 추가하고 sum, cnt 업데이트 후 큐에 넣기
						c.allies.add(new Point(nx, ny));
						c.sum+=tmpMap[nx][ny];
						c.cnt++;
						dq.offerLast(new Point(nx, ny));
					}
				}
			}
		}
		return c;
	}	
}
