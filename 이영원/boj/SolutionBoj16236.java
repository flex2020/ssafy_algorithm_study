import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.Point;

public class Main {

	static int answer, N, M;
	static PriorityQueue<Fish> pq;
	static Deque<Fish> dq;
	static Shark shark;
	static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
	static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상
	static int[][] field;
	
	static class Fish{
	    int x, y, dist, size;
	
	    public Fish(int x, int y, int dist, int size) {
	        super();
	        this.x = x;
	        this.y = y;
	        this.dist = dist;
	        this.size = size;
	    }
	
	    @Override
	    public String toString() {
	        return "Fish{" +
	                "x=" + x +
	                ", y=" + y +
	                ", dist=" + dist +
	                ", size=" + size +
	                '}';
	    }
	}
	
	static class Shark{
	    int x, y, size, eat;
	
	    public Shark(int x, int y, int size, int eat) {
	        super();
	        this.x = x;
	        this.y = y;
	        this.size = size;
	        this.eat = eat;
	    }
	
	    @Override
	    public String toString() {
	        return "Shark{" +
	                "x=" + x +
	                ", y=" + y +
	                ", size=" + size +
	                ", eat=" + eat +
	                '}';
	    }
	}
	
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    int time=0;
	//    dq = new ArrayDeque<>();
	
	    field = new int[N+2][N+2];
	    Arrays.fill(field[0], -1);
	    Arrays.fill(field[N+1], -1);
	    for (int i = 1; i < N+1; i++) { // 테두리 채우기
	        st = new StringTokenizer(br.readLine());
	        for (int j = 1; j < N+1; j++) {
	            field[i][j] = Integer.parseInt(st.nextToken());
	            if(field[i][j]==9) shark = new Shark(i, j, 2, 0);
	//            else if(field[i][j]!=0) {
	//                dq.offerLast(new Fish(i, j, -1, field[i][j]));
	//            }
	//                if(field[i][j]==9) field[i][j]=12;
	        }
	            field[i][0]=-1;
	            field[i][N+1]=-1;
	    }
	    

	    // 거리가 가깝고, 가까운게 여러개라면 x가 작은거, x가 작은게 여러개라면 y가 작은 순으로 정렬
	    pq = new PriorityQueue<>((a,b)->{
	        if(a.dist==b.dist) {
	            if(a.x==b.x) return a.y-b.y;
	            return a.x-b.x;
	        }
	        return a.dist-b.dist;
	    });
	
	    while(true){
	    	pq.clear(); // pq 초기화
	        bfs(); // 가까운거 pq에 넣기
//	        System.out.println(pq);
//	        print();
//	        Thread.sleep(1000);
	        Fish f = pq.poll(); // 가장 알맞는거 뽑기
	        if(f==null) break; // pq에 아무것도 없다면 탈출
	        // 상어 위치 조정, 먹고 사이즈 키우기
	        field[f.x][f.y]=9; 
	        field[shark.x][shark.y]=0;
	        shark.x = f.x;
	        shark.y = f.y;
	        shark.eat++;
	        if(shark.eat==shark.size) {
	        	shark.eat=0;
	        	shark.size++;
	        }
	        
	        // 이동거리만큼 시간에 추가
	    	time+=f.dist;
//	    	System.out.println("time = " + time + " " + shark);
//	        print();
	    } 
	    
	    System.out.println(time);
	
	}
	
	private static void bfs() {
		Deque<Point> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N+2][N+2];
		dq.offerLast(new Point(shark.x, shark.y));
		int idx=1;
		while(!dq.isEmpty()) {
			int dqSize = dq.size(); // depth만큼 반복해서 뽑아주기
			for (int i = 0; i < dqSize; i++) {
				Point p = dq.pollFirst();
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					if(field[nx][ny]!=-1 && !visited[nx][ny] && field[nx][ny]<=shark.size) {
						visited[nx][ny]=true;
						// 물고기고, 물고기 크기가 상어보다 작다면 pq에 추가
						if(field[nx][ny]<=6 && field[nx][ny]>0 && field[nx][ny] < shark.size) pq.add(new Fish(nx, ny, idx, field[nx][ny]));
						dq.offerLast(new Point(nx, ny)); // 일단 여기까지 오면 dq에는 추가해준다. 이동이 가능하다는 뜻
					}
				}
			}
			idx++;
		}
	}
	
	private static void print() {
	    for (int i = 1; i < field.length-1; i++) {
			for (int j = 1; j < field.length-1; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
	    System.out.println();
	}
}
