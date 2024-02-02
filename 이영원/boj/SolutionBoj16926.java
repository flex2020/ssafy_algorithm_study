import java.io.BufferedReader;
import java.awt.Point;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	static int[][] ans;

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	
	    st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken()); // 행
	    int M = Integer.parseInt(st.nextToken()); // 열
	    int R = Integer.parseInt(st.nextToken()); // 회전수
	    
	    arr = new int[N][M];
	    ans = new int[N][M];
	    
	    // 배열 내에서 반시계 사이클이 몇번 돌아가야하는지 구하기
	    int repeat = Math.min(N, M);
	    repeat = (repeat%2!=0) ? repeat/2+1 : repeat/2;
	    
	    for(int i=0;i<N;i++) { // 초기 배열 입력
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0;j<M;j++) {
	    		arr[i][j]=Integer.parseInt(st.nextToken());
	    	}
	    }
	    
	    for(int i=0;i<repeat;i++) {
	    	turn(N-2*i, M-2*i, ((N-(2*i)+M-(2*i)-2)*2), R, i);
	    }
	    
	    for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].length; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	    
	    
	}
	
	private static void turn(int N, int M, int cycle, int R, int i) { // 돌리는 함수
		int r = R%cycle; // 어디로 옮길지 정한다.
		Point startP;
		Point targetP;
		int cnt=0;
		int target=0;
		for(int start=0;start<cycle;start++) {
			target=(start+r)%cycle;
			startP = find(start, N, M);
			targetP = find(target, N, M);
			ans[targetP.x+i][targetP.y+i]=arr[startP.x+i][startP.y+i];
		}
	}
	
//	T< N이라면 x만 그만큼 증가
//	N<=T<=(N-1)+(M-1) 이라면 x = N-1, y 는 T-(N-1) 만큼 증가
//	(N-1)+(M-1)<T<2*(N-1)+M-1이라면 x는 2(N+M-2)-T-(M-1), y = (M-1)
//	이외에는 x는 0, y는 2(N+M-2)-T
	private static Point find(int t, int N, int M) {
		int x;
		int y;
		if(t<N) {
			x=t;
			y=0;
		}else if(t<=(N-1)+(M-1)) {
			x=N-1;
			y=t-(N-1);
		}else if(t<2*(N-1)+M-1) {
			x = 2*(N+M-2)-t-(M-1);
			y = M-1;
		}else {
			x=0;
			y=2*(N+M-2)-t;
		}
		return new Point(x,y);
	}
}
