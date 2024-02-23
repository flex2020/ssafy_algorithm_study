import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 진짜 못한 풀이. 다시는 이렇게 생각하지 말자.
public class Main {
	
	static int[] arr;
	static int[] sel;
	static boolean[] visited;
	static int[][] inningResult;
	
	static int[][] map;
	static int N;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		arr = new int[9];
		sel = new int[9];
		visited = new boolean[9];
		inningResult = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inningResult[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		for (int i = 0; i < arr.length; i++) {
			arr[i]=i;
		}
		sel[3]=0; // 1번 선수는 4번 타자로 고정
		visited[0]=true;
		perm(0);
		
		System.out.println(answer);
		
	}
	
	// 실제 게임하기. 
	private static void play() {
		int num=0;
		int result=0;
		int plus=0;
		boolean[][] base = new boolean[2][3]; // base 현재 상황 기록
		int now=0;
//		if(sel[0]==3 && sel[1]==1 && sel[2]==8 && sel[3]==0 && sel[4]==5 && sel[5]==6
//				&& sel[6]==2 && sel[7]==4 && sel[8]==7 ) {
//			System.out.println(Arrays.toString(sel));
//		}
		for (int i = 0; i < N; i++) { // 이닝별로
			int out=0;
			base = new boolean[2][3];
			now=0;
			for (int j = num; out < 3; j=(j+1)%9) { //각 타수
				int inResult = inningResult[i][sel[j]];
				if(inResult==0) { // 아웃이면 현재 상황 그대로 옮겨주고 outCount 올려주기
					out++;
					for(int k=0; k<base[now].length;k++) {
						base[(now+1)%2][k] = base[now][k];
					}
				}
				else { // 아니면 베이스 상황 업데이트
					for(int k=0 ; k<base[now].length ; k++) {
						if(base[now][k]) {
							if(k+inResult>=base[now].length) {
								result++;
							}else {
								base[(now+1)%2][k+inResult]=true;
							}
						}
					}
					if(inResult!=4) base[(now+1)%2][inResult-1]=true;
					else result++;
				}
				if(out==3) num = (j+1)%9; // out이면 인덱스 상황 기록
				base[now]=new boolean[3]; // 이전 기록지 초기화
				now = (now+1)%2; // 기록한 기록지로 포인터 옮김
//				if(sel[0]==3 && sel[1]==1 && sel[2]==8 && sel[3]==0 && sel[4]==5 && sel[5]==6
//						&& sel[6]==2 && sel[7]==4 && sel[8]==7 ) {
//					System.out.println(inResult + "루타 쳤어요. 현재 스코어 : " + result + " 아웃 수 : " + out + " 현재 진루 상황 : " + base[now][0] + " " + base[now][1] + " " + base[now][2]);
//				}
			}
		}
//		if(result==80) {
//			System.out.println(Arrays.toString(sel));
//		}
		answer = Math.max(answer, result);
	}
	
	private static void perm(int cnt) {
		// basis part
		if(cnt == arr.length) {
//			System.out.println(Arrays.toString(sel));
			play();
			return;
		}
		// inductive part
		for (int i = 1; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i]=true;
				sel[cnt]=arr[i];
				if(cnt+1==3) { // 4번 타자 결정하려고 하면 그냥 다음으로 넘겨버리기
					perm(cnt+2);
				}else {
					perm(cnt+1);
				}
				visited[i]=false;
			}
		}
	}
	
}
