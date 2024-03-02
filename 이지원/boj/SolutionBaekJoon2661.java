import java.util.*;
import java.io.*;

public class SolutionBaekJoon2661 {
	
	static int[] nums = {1, 2, 3};
	static int N;
	static int[] results;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		results = new int[N];
		calculateResult(0);
		
		StringBuilder sb = new StringBuilder();
		for (int result : results) {
			sb.append(result);
		}
		
		System.out.println(sb);
	}
	
	public static boolean calculateResult(int count) {
		if (count == N) {
			return true;
		}
		
		for (int i = 0; i < nums.length; i++) {
			results[count] = nums[i];
			
			// 유효한 숫자인지 확인하기
			if (isValidate(count)) {
				if (calculateResult(count + 1)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isValidate(int endIndex) {
		int num = 1;
		while ((endIndex + 1) / num > 1) {
			StringBuilder a = new StringBuilder();
			StringBuilder b = new StringBuilder();
			for (int index = endIndex; index > endIndex - num; index--) {
				a.append(results[index]);
				b.append(results[index - num]);
			}
			
			if (a.toString().equals(b.toString())) {
				return false;
			}
			
			num++;
		}
		
		return true;
	}

}
