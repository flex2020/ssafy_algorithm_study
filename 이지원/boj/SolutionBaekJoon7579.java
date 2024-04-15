import java.util.*;
import java.io.*;

public class SolutionBaekJoon7579 {
	
	static final int MAX_COST = 10000;
	static int[] memories;
	static int[] costs;
	static int[] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 활성화 앱 메모리 입력받기
		memories = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < memories.length; i++) {
			memories[i] = Integer.parseInt(st.nextToken());
		}
		
		// 활성화 앱 비활성화 비용 입력받기
		costs = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < costs.length; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		memo = new int[MAX_COST + 1];
		for (int num = 0; num < N; num++) {
			for (int cost = MAX_COST; cost >= costs[num]; cost--) {
				memo[cost] = Math.max(memo[cost - costs[num]] + memories[num], memo[cost]);
			}
		}
		
		for (int cost = 0; cost <= MAX_COST; cost++) {
			if (memo[cost] >= M) {
				System.out.println(cost);
				break;
			}
		}
	}

}
