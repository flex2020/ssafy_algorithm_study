import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.awt.Point;


// 동시에가 나오면 2차원 배열이 아니라 다른 관점으로 생각해보도록 하자. 일단 이렇게 푸는게 아니라는건 알았다.
public class Main {
	
	static int N, M, D;
	static int enemy; // 적 수
	static int answer; // 정답(최대치)
	static int[][] map;
	static int[] sel; // 선택한 조합

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sel = new int[3];
		
		enemy=0;
		answer=0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemy++;
			}
		}
		
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	// 조합 찾고 게임 플레이
	private static void comb(int idx, int cnt) {
		if(answer==enemy) return;
		//basis part
		if(cnt==3) {
			//System.out.println(Arrays.toString(sel));
			play();
			//System.out.println(answer + " " + enemy);
			return;
		}
		
		// inductive part
		for(int i=idx;i<M;i++) {
			sel[cnt]=i;
			comb(i+1, cnt+1);
			if(answer==enemy) return;
		}
	}
	
	private static void play() {
		int result=0;
		int e = enemy;
		int get=0;
		int trash=0;
		int[][] field = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				field[i][j]=map[i][j];
			}
		}
		// 적이 사라질 때까지 진행
		while(e!=0) {
			if(answer >= e+result) return; // e+result가 answer보다 작거나 같다면 더 해도 의미없음
			get=shoot(field);
			trash=move(e-get, field);
			e-=(get+trash);
			result+=get;
			//System.out.println(e);
		}
		// answer==enemy라면 더 줄일 수 있을지도?
		answer=Math.max(answer, result);
	}
	
	// 쏘는 메소드
	private static int shoot(int[][] field) {
		int result=0;
		// 중복처리를 위한 points Set 선언
		Set<Point> points = new HashSet<>();
		Point[] arcP = new Point[3];
		for (int i = 0; i < D; i++) { // 거리만큼 있는지 체크
			for (int j = 0; j < sel.length; j++) { // 모든 궁수 시행 확인
				// null이 아니면 이미 가장 최적의 타겟이 정해졌기 때문에 continue
				if(arcP[j]!=null) continue;
				Point p = check(i, sel[j], field);
				//System.out.println(p.x + " " + p.y);
				if(p.x!=-1 && p.y!=-1) { // 있으면 타겟팅하기
					points.add(new Point(p.x, p.y));
					arcP[j] = new Point(p.x, p.y);
					continue;
				}
			}
		}
		
		// set에 있는거 죽이기
		for(Point p : points) {
			field[p.x][p.y]=0;
			result++;
		}
		
//		for (int i = 0; i < arcP.length; i++) {
//			if(arcP[i]!=null) {
//				field[arcP[i].x][arcP[i].y]=0;
//			}
//		}
		return result;
	}
	
	// 궁수 기준 해당 거리만큼의 위치에 적이 있는지를 왼쪽부터 스캐닝하기
	private static Point check(int dist, int arcPos, int[][] field) {
//		System.out.println(dist + " " + arcPos);
		// 왼쪽부터 가운데까지 확인
		for (int i = N-1, j=arcPos-dist; i >= N-1-dist; i--, j++) {
//			System.out.println(i + " " + j);
			if(i<N && i>=0 && j<M && j>=0) {
//				System.out.println(i + " " + j);
				if(field[i][j] == 1) return new Point(i, j);
			}
		}
		// 가운데부터 오른쪽까지 확인
		for (int i = N-1-dist+1, j=arcPos+1; i <= N-1; i++, j++) {
			if(i<N && i>=0 && j<M && j>=0) {
				if(field[i][j] == 1) return new Point(i, j);
			}
		}
		// 없으면 -1, -1 리턴
		return new Point(-1, -1);
	}
	
	// 적 아래로 이동
	private static int move(int e, int[][] field) {
		int result=0;
		for (int i = N-1; i >= 0; i--) {
			if(e-result==0) return result;
			for (int j = 0; j < M; j++) {
				if(field[i][j]==1) {
					if(i==N-1) {
						field[i][j]=0;
						result++;
					}
					else {
						field[i][j]=0;
						field[i+1][j]=1;
					}
				}
				if(e-result==0) return result;
			}
		}
		return result;
	}
}
