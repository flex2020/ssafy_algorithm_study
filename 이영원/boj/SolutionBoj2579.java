import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[num+1][2];
		int[] stairs = new int[num+1];
		
		for (int i = 1; i < stairs.length; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		arr[1][0]=stairs[1];
		
		for(int i=0;i<=num;i++) {
			for(int j=0;j<2;j++) {
				if(arr[i][j]!=0 || i==0) {
					if(i+2<=num) {
						arr[i+2][0] = Math.max(arr[i+2][0], arr[i][j]+stairs[i+2]);
					}
					if(i+1<=num && j+1<2) {
						arr[i+1][j+1] = Math.max(arr[i+1][j+1], arr[i][j]+stairs[i+1]);
					}
				}
			}
		}
		int answer = Math.max(arr[num][0], arr[num][1]);
//		answer = Math.max(answer, arr[num][2]);
		System.out.println(answer);
	}
	
}
