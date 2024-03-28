import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		int[] arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=n;i++) {
			int idx=1;
			while(true) {
				if(dp[idx]==0 || arr[i]>=dp[idx]) {
					dp[idx]=arr[i];
					break;
				}
				idx++;
			}
//			System.out.println(Arrays.toString(dp));
		}
		
		int answer=1;
		for(int i=n;i>=1;i--) {
			if(dp[i]!=0) {
				answer=i;
				break;
			}
		}
		
		System.out.println(answer);
		
	}
	
}
