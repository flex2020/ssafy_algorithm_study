import java.io.*;
import java.util.*;
public class Solution_4050 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Integer[] nums = new Integer[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums, (n1, n2) -> n2 - n1);
			int answer = 0;
			for (int i=0; i<N; i++) {
				if (i % 3 != 2) {
					answer += nums[i];
				}
			}
			System.out.printf("#%d %d\n", tc, answer);
		}

	}

}
