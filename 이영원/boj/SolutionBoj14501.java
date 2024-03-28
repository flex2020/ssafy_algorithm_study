import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[] T = new int[N+1]; // 기간
		int[] P = new int[N+1]; // 가격
		int[] dp = new int[N+2]; // dp
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			if(i+(T[i]-1) <= N) {
				for(int j=i+T[i];j<=N+1;j++) {
					dp[j]=Math.max(dp[j], dp[i]+P[i]);
				}

			}
//			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[N+1]);
		
	}
	
}
