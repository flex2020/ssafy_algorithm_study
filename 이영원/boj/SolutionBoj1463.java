import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		int[] arr = new int[num+1];
		int idx=num;
		for (int i = num-1; i >= 1; i--) {
			if(i*3<=num && (i*3!=0 || i*3==num)) {
				if(arr[i]==0) {
					arr[i]=arr[i*3]+1;	
				}else {
					arr[i]=Math.min(arr[i], arr[i*3]+1);
				}
			}
			if(i*2<=num && (i*2!=0 || i*2==num)) {
				if(arr[i]==0) {
					arr[i]=arr[i*2]+1;	
				}else {
					arr[i]=Math.min(arr[i], arr[i*2]+1);
				}
			}
			if(i<=num-1){
				if(arr[i]==0) {
					arr[i]=arr[i+1]+1;
				}else {
					arr[i]=Math.min(arr[i], arr[i+1]+1);
				}
			}
			
		}
		
		System.out.println(arr[1]);
	}
	
}
