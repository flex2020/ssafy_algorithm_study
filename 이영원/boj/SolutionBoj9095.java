import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] arr = new int[12];
		arr[1]=1;
		arr[2]=2;
		arr[3]=4;
		
		for(int t=0;t<T;t++) {

			int num = Integer.parseInt(br.readLine());
			for(int i=4;i<=num;i++) {
				int n = arr[i-1]*2 + arr[i-2]*2 + arr[i-3]*2;
				if(arr[i]!=0) continue;
				arr[i] = n/2;
			}
			
			System.out.println(arr[num]);
		}
		

	}
	
}
