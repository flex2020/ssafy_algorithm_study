import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
	
	static int n,m,k,c; // 맵 크기, 몇년, 제초제 확산범위, 제초제 잔류연도
	
	static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1}; // 상 하 좌 우 우상 우하 좌하 좌상
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1}; // 상 하 좌 우 우상 우하 좌하 좌상
	
	static Tree[][] map;
	
	static int answer;
	
	static class Tree{
		int num, remain, dead; // 숫자, 번식가능한 칸 수, 제초제가 뿌려졌는지

		@Override
		public String toString() {
			return "Tree [num=" + num + ", remain=" + remain + ", dead=" + dead + "]";
		}
	}
	
	static class Weeding{
		int x, y, num; // 좌표, 제거가능수

		public Weeding(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Weeding [x=" + x + ", y=" + y + ", num=" + num + "]";
		}
		
		
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	answer=0;
    	
    	map = new Tree[n][n];
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = new Tree();
				map[i][j].num = Integer.parseInt(st.nextToken());
			}
		}
    	
    	for (int i = 0; i < m; i++) {
//    		print();
    		update();
//    		print();
			grow();
//			print();
			breed();
//			print();
			kill();
//			print();
		}
    	
    	System.out.println(answer);
    	
    }
    
    // 년도에 맞춰서 제초제 잔류연도 낮추기
    private static void update() {
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(map[i][j].dead!=0) map[i][j].dead--;
    		}
    	}
    }
    
    // 키우기 + remain값 계산
    private static void grow() {
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j].num>0) {
					map[i][j].remain=0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny].dead!=0) continue;
						if(map[nx][ny].num==0) {
							map[i][j].remain++;
						}else if(map[nx][ny].num>0) {
							map[i][j].num++;
						}
					}
				}
			}
		}
    }
    
    // 번식하기 -> Map에 담고 한번에 진행
    private static void breed() {
    	HashMap<Point, Integer> breedMap = new HashMap<>();
    	for (int i = 0; i < n; i++) {
			L: for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny].dead!=0 || map[nx][ny].num<0) continue;
					if(map[i][j].remain==0) continue L;
					if(map[nx][ny].num==0) {
						if(breedMap.containsKey(new Point(nx, ny))) { // 있으면 업데이트
							breedMap.put(new Point(nx, ny), breedMap.get(new Point(nx, ny))+map[i][j].num/map[i][j].remain);
						}else { // 없으면 생성
							breedMap.put(new Point(nx, ny), map[i][j].num/map[i][j].remain);
						}
					}
				}
			}
		}
    	
    	System.out.println(breedMap);
    	
    	for(Point p : breedMap.keySet()) { // 맵에 있는 값으로 업데이트
    		map[p.x][p.y].num = breedMap.get(p);
    	}
    }
    
    // 죽이기
    private static void kill() {
    	// 크고 행, 열이 작은 순으로 pq
    	PriorityQueue<Weeding> pq = new PriorityQueue<>((a,b) -> {
    		if(a.num==b.num) {
    			if(a.x==b.x) {
    				return a.y-b.y;
    			}
    			return a.x-b.x;
    		}
    		return b.num-a.num;
    	});
    	
    	// 제초값 계산해서 pq에 넣기
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j].num>0) {
					int num=map[i][j].num;
					L: for(int d=4;d<8;d++) {
						int nx = i;
						int ny = j;
						for(int l=0;l<k;l++) {
							nx+=dx[d];
							ny+=dy[d];
							if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny].num<=0) continue L;
							num+=map[nx][ny].num;
						}
					}
					pq.offer(new Weeding(i, j, num));
				}
			}
		}
    	
    	System.out.println(pq);
    	
    	// 하나 뽑기
    	Weeding weed = pq.poll();
    	// null이 나온게 아니면 실제로 지우기
    	if(weed!=null) {
    		answer+=weed.num;
    		map[weed.x][weed.y].num=0;
    		map[weed.x][weed.y].dead=c+1;
			L: for(int d=4;d<8;d++) {
				int nx = weed.x;
				int ny = weed.y;
				for(int l=0;l<k;l++) {
					nx+=dx[d];
					ny+=dy[d];
					if(nx<0 || nx>=n || ny<0 || ny>=n) continue L;
					if(map[nx][ny].num<=0) {
						map[nx][ny].dead = c+1;
						continue L;
					}
					map[nx][ny].num=0;
					map[nx][ny].dead=c+1;
				}
			}
    	}
    }
    
    private static void print() {
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]+ " ");
			}
			System.out.println();
		}
    	System.out.println();
    }
}
