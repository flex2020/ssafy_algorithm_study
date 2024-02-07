import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static int[][][] arr;
	static int ans;
	static int n;
	static int m;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		n=N;
		m=M;
		int max = Math.max(N, M);
		
		arr = new int[2][max][max];
		ans=0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[0][i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int[] cmds = new int[R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cmds.length; i++) {
			cmds[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<cmds.length;i++) {
			switch(cmds[i]) {
				case 1:
				case 2:
					reverse(R, cmds[i]);
					break;
				case 3:
				case 4:
					turn(R, cmds[i]);
					break;
				case 5:
				case 6:
					rotate(R, cmds[i]);
					break;
			}
		}
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				sb.append(arr[ans][i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}
	
	private static void rotate(int R, int cmd) {
		int partN = n/2;
		int partM = m/2;

		if(cmd==5) {
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < m/2; j++) {
					arr[(ans+1)%2][i][j]=arr[ans][partN+i][j];
					arr[(ans+1)%2][i][partM+j]=arr[ans][i][j];
					arr[(ans+1)%2][partN+i][partM+j]=arr[ans][i][partM+j];
					arr[(ans+1)%2][partN+i][j]=arr[ans][partN+i][partM+j];
				}
			}
		}else {
			for (int i = 0; i < n/2; i++) {
				for (int j = 0; j < m/2; j++) {
					arr[(ans+1)%2][i][j]=arr[ans][i][partM+j];
					arr[(ans+1)%2][i][partM+j]=arr[ans][partN+i][partM+j];
					arr[(ans+1)%2][partN+i][partM+j]=arr[ans][partN+i][j];
					arr[(ans+1)%2][partN+i][j]=arr[ans][i][j];
				}
			}
		}
		ans=(ans+1)%2;
		
		
	}

	private static void turn(int R, int cmd) {
		int tmp=0;
		
		if(cmd==3) {
			tmp=n;
			n=m;
			m=tmp;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[(ans+1)%2][i][j]=arr[ans][m-1-j][i];
				}
			}
		}else {
			tmp=n;
			n=m;
			m=tmp;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[(ans+1)%2][i][j]=arr[ans][j][n-1-i];
				}
			}
		}
		ans=(ans+1)%2;
	}

	private static void reverse(int R, int cmd) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(cmd==1) {
					arr[(ans+1)%2][i][j]=arr[ans][n-1-i][j];
				}else {
					arr[(ans+1)%2][i][j]=arr[ans][i][m-1-j];
				}
			}
		}
		ans=(ans+1)%2;
	}
}
