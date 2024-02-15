import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer;
	static StringBuilder sb;
	static int[][][] worldcup;
	//static int[][] check; // 1은 승리, 2는 무승부, 3은 패배
	//static int num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		worldcup = new int[4][6][3];
		// check = new int[6][6];
		// num=0;
		sb = new StringBuilder();
		
		for(int i=0;i<4;i++) { // 입력받기
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				worldcup[i][j][0]=Integer.parseInt(st.nextToken());
				worldcup[i][j][1]=Integer.parseInt(st.nextToken());
				worldcup[i][j][2]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if(!garo(i)) {
				sb.append(0).append(" ");
				continue;
			}
			if(check(i)) {
				sb.append(1).append(" ");
			}else {
				sb.append(0).append(" ");
			}
//			System.out.println((i+1) + "번 끝");
		}
		
		System.out.println(sb);
	}
	
	private static boolean garo(int idx) { // 합이 5가 되는지 확인
		for (int i = 0; i < 6; i++) {
			if(worldcup[idx][i][0]+worldcup[idx][i][1]+worldcup[idx][i][2] != 5) {
				return false;
			}
		}
		return true;
	}
	
	// 해당 점수가 맞는지 확인
	private static boolean check(int idx) throws InterruptedException {
		// num=idx;
		// check = new int[6][6]; // 체크배열 초기화
		if(recursive(worldcup[idx], 0, 0, 5)) {
			return true;
		}
		return false;
	}
	
	// 백트래킹을 이용해서 구하기. cup을 올리고 원복하고를 반복하기.
	// 2차원배열, 카운트, 인덱스, 사이즈
	private static boolean recursive(int[][] cup, int cnt, int idx, int size) throws InterruptedException {
//		print();
//		Thread.sleep(100);
		// basis part
		// 이건 필요없을듯?
		if(cup[idx][0]<0 || cup[idx][1]<0 || cup[idx][2]<0) return false;
		if(cnt==size) {
			if(idx==5) {
//				print();
//				Thread.sleep(10000);
//				if(worldcup[wcIdx][idx][0]<0 || worldcup[wcIdx][idx][1]<0 || worldcup[wcIdx][idx][2]<0) return false;
				return true;
			}
			if(recursive(cup, 0, idx+1, size-1)) return true;
			return false;
		}
		
		// inductive part
		for(int i=idx+1+cnt;i<6;i++) {
			cup[idx][0]--;
			cup[i][2]--;
			if(cup[idx][0]>=0 && cup[i][2]>=0) {
				if(recursive(cup, cnt+1, idx, size)) return true;
			}
			cup[idx][0]++;
			cup[i][2]++;
			
			cup[idx][1]--;
			cup[i][1]--;
			if(cup[idx][1]>=0 && cup[i][1]>=0) {
				if(recursive(cup, cnt+1, idx, size)) return true;
			}
			cup[idx][1]++;
			cup[i][1]++;
			
			cup[idx][2]--;
			cup[i][0]--;
			if(cup[idx][2]>=0 && cup[i][0]>=0) {
				if(recursive(cup, cnt+1, idx, size)) return true;
			}
			cup[idx][2]++;
			cup[i][0]++;
		}
		return false;
	}
}
