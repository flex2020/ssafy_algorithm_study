import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
	
	// 상하좌우
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	
	static List<Integer[][]> captureCnt;
	static List<Character[][]> captureArr;
	static int num=0;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int R = Integer.parseInt(st.nextToken()); // 행
		int C = Integer.parseInt(st.nextToken()); // 열
		int N = Integer.parseInt(st.nextToken()); // 몇초후 보여줄건지
		int cycle = 0; // 사이클
		
		Character[][] arr=new Character[R][C]; // 그림 배열
		Integer[][] cnt=new Integer[R][C]; // 카운트다운 배열
		
		captureCnt = new ArrayList<>(); // 카운트다운을 담을 스냅샷, 캡쳐 리스트
		captureArr = new ArrayList<>(); // 그림을 담을 스냅샷, 캡쳐 리스트
		
		for(int i=0;i<R;i++) { // 격자입력
			String input = br.readLine();
			for(int j=0;j<C;j++) {
				arr[i][j]=input.charAt(j);
				if(arr[i][j]=='O') {
					cnt[i][j]=1; // 폭탄칸
				}else {
					cnt[i][j]=-1; // 그냥칸
				}
			}
		}
		
		// 초기값 저장하기
		captureCnt.add((Integer[][])copy(cnt));
		captureArr.add((Character[][])copy(arr));
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}

		for(int t=2;t<=N;t++) {
			// 십자 펑! 시키기
			if(t%2==1) { // 아무것도 안함 + 폭발
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if(cnt[i][j]==0 && arr[i][j]=='O') { // 폭발해야하는 폭발칸인 경우 십자폭발 진행
							for(int k=0;k<dr.length;k++) {
								arr[i][j]='.';
								cnt[i][j]=-1;
								int nr=i+dr[k];
								int nc=j+dc[k];
								if(nr>=0 && nr<R && nc>=0 && nc<C && cnt[nr][nc]!=0) {
									arr[nr][nc]='.';
									cnt[nr][nc]=-1;
								}
							}
						}else if(cnt[i][j]>0) {
							cnt[i][j]--;
						}
					}
				}
			}else { // 설치(다채워짐)
				for(int i=0;i<R;i++) {
					for(int j=0;j<C;j++) {
						if(cnt[i][j]==-1) {
							cnt[i][j]=2;
							arr[i][j]='O';
						}else {
							cnt[i][j]--;
						}
					}
				}
			}
			if(searchSame(cnt)) { // 사이클이 찾아지면
				if(num!=0) { // num이 0, 초기값이 사이클에 포함되지 않은경우
					for(int i=0;i<num;i++) { // 포함되지 않는만큼 빼주기
						captureArr.remove(0); 
					}
				}
				cycle=t-1-num; // 뺀만큼 사이클 조정
				break;
			}else { // 아니라면 깊은복사해서 리스트에 저장하기
				captureCnt.add((Integer[][])copy(cnt));
				captureArr.add((Character[][])copy(arr));
//				System.out.println(captureCnt.size());
			}
		}
		
//		System.out.println(captureCnt.size());
		if(cycle!=0) arr=captureArr.get((N-1-num)%cycle); // 사이클 나머지 연산으로 arr 값 찾기
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb); // 출력
		
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}

		
	}
	
	// 같은건지 검색하는 메소드(cnt 이용)
	private static boolean searchSame(Integer[][] cnt) {
		num=0;
		int idx=0;
//		System.out.println("arr");
//		for(int i=0;i<arr.length;i++) {
//			for(int j=0;j<arr[i].length;j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		Loop: 
		for(Integer[][] c : captureCnt) {
//			System.out.println("c");
//			for(int i=0;i<arr.length;i++) {
//				for(int j=0;j<arr[i].length;j++) {
//					System.out.print(c[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			for(int i=0;i<cnt.length;i++) {
				for(int j=0;j<cnt[i].length;j++) {
					if(c[i][j]!=cnt[i][j]) { // 초기값이 사이클에 포함되지 않은경우
						idx++; // idx 올리기
						continue Loop; // Loop로 바로 다음껄로 탐색
					}
				}
			}
			num=idx; // 하기
			return true; // true
		}
		return false;
	}
	
	// Character[][]와 Integer[][]을 깊은복사하기 위한 다형성을 이용한 static 메소드
	private static Object[][] copy(Object[][] arr){
		Object[][] newArr = null;
		if(arr instanceof Integer[][]) {
			newArr = new Integer[arr.length][arr[0].length];
		}
		else if(arr instanceof Character[][]) {
			newArr = new Character[arr.length][arr[0].length];
		}
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				newArr[i][j]=arr[i][j];
			}
		}
		return newArr;
	}

}
