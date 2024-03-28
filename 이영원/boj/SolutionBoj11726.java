import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		arr[1]=1;
		if(n>=2) {
			arr[2]=2;
		}
		if(n>=3) {
			arr[3]=3;
		}
		
		for(int i=4;i<=n;i++) {
			arr[i]=((arr[i-1]*2 + arr[i-2]*2)/2)%10007;
		}
		
		System.out.println(arr[n]);
		
	}
	
}
