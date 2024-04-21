import java.util.*;
import java.io.*;

public class SolutionBaekJoon2295 {
	
	static int[] nums;
	static List<Integer> sums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 숫자 입력받기
		nums = new int[N];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		// 두 수의 합 구하기
		sums = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sums.add(nums[i] + nums[j]);
			}
		}
		
		// 두 수의 합 정렬하기
		Collections.sort(sums);
		
		// 세 수의 합 최대 구하기
		Arrays.sort(nums);
		int result = 0;
		aa: for (int kIdx = N - 1; kIdx > -1; kIdx--) {
			int k = nums[kIdx];
			for (int zIdx = kIdx - 1; zIdx > -1; zIdx--) {
				int z = nums[zIdx];
				if (isExisted(k - z)) {
					result = k;
					break aa;
				}
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean isExisted(int num) {
		int start = 0, end = sums.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int midNum = sums.get(mid);
			
			if (num < midNum) {
				end = mid - 1;
			} else if (num > midNum) {
				start = mid + 1;
			} else {
				return true;
			}
		}
		
		return false;
	}

}
