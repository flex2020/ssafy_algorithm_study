import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int[] dwarf;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		dwarf = new int[9];
		
		for(int i=0;i<dwarf.length;i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		
		comb(new int[7], 0, 0, 0);
		
	}
	
	private static boolean comb(int[] arr, int cnt, int sum, int idx) {
		//basis part
		if(cnt==7 && sum==100) {
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
			return true;
		}else if(cnt==7) return false;
		
		for (int i = idx; i < dwarf.length; i++) {
			arr[cnt] = dwarf[i];
			if(comb(arr, cnt+1, sum+dwarf[i], i+1)) return true;
			if(comb(arr, cnt, sum, i+1)) return true; // 안뽑는거
		}
		return false;
	}
}
