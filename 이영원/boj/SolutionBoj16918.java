import java.util.Scanner;

public class Main {

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt(); // 행
		int C = sc.nextInt(); // 열
		int N = sc.nextInt(); // 몇초후 보여줄건지
		
		// 상하좌우
		int[] dr= {-1,1,0,0};
		int[] dc= {0,0,-1,1};
		
		char[][] arr=new char[R][C];
		int[][] cnt=new int[R][C];
		
		for(int i=0;i<R;i++) { // 격자입력
			String input = sc.next();
			for(int j=0;j<C;j++) {
				arr[i][j]=input.charAt(j);
				if(arr[i][j]=='O') {
					cnt[i][j]=1;
				}else {
					cnt[i][j]=-1;
				}
			}
		}
		

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
		}
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}

		
	}

}
