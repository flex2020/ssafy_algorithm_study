import java.util.*;
import java.io.*;

public class SolutionBaekJoon17404 {
	
	static int[][] costs;  // i: 집 번호, j: 색깔(RGB)
	// RR, RG, RB, GR, GG, GB, BR, BG, BB
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 집을 칠하는 비용 입력받기
		costs = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < costs.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < costs[0].length; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (N % 2 == 0) {
			memo = new int[N / 2][9];
		} else {
			memo = new int[N / 2 + 1][9];
		}
		
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], Integer.MAX_VALUE);
		}

		// 1, N번째 집 칠하는 비용 계산하기
		for (int start = 0; start < 3; start++) {
			for (int end = 0; end < 3; end++) {
				if (start == end) {
					continue;
				}
				
				memo[0][3 * start + end] = costs[0][start] + costs[N - 1][end];
			}
		}
		
		// 2 ~ N-1번째 집 질하는 비용 계산하기
		int maxDepth = memo.length - 1;
		int startHouse = 1, endHouse = N - 2;
		for (int depth = 1; depth < memo.length; depth++) {
			if (startHouse == endHouse) {  // 시작집 = 끝집
				for (int start = 0; start < 3; start++) {
					int cost = costs[startHouse][start];
					
					int preMinCost = Integer.MAX_VALUE;
					for (int preStart = 0; preStart < 3; preStart++) {
						// N/2+1번째 집 색깔 = N/2번째 집 색깔
						if (preStart == start) {
							continue;
						}
						
						for (int preEnd = 0; preEnd < 3; preEnd++) {
							// N/2+1번쨰 집 색깔 = N/2+2번째 집 색깔
							if (preEnd == start) {
								continue;
							}
							
							preMinCost = Math.min(preMinCost, memo[depth - 1][3 * preStart + preEnd]);
						}
						
						memo[depth][3 * start + start] = cost + preMinCost;
					}
				}
			} else {
				for (int start = 0; start < 3; start++) {
					for (int end = 0; end < 3; end++) {
						// N/2번째 집의 색깔 = N/2+1번째 집의 색깔
						if (depth == maxDepth && start == end) {
							continue;
						}
						
						int cost = costs[startHouse][start] + costs[endHouse][end];
						
						int preMinCost = Integer.MAX_VALUE;
						for (int preStart = 0; preStart < 3; preStart++) {
							// 시작집 색깔 = 시작집의 왼쪽집 색깔
							if (preStart == start) {
								continue;
							}
							
							for (int preEnd = 0; preEnd < 3; preEnd++) {
								// 끝집의 색깔 = 끝집의 오른쪽집 색깔
								if (preEnd == end) {
									continue;
								}
								
								preMinCost = Math.min(preMinCost, memo[depth - 1][3 * preStart + preEnd]);
							}
						}
						
						memo[depth][3 * start + end] = cost + preMinCost;
					}
				}
			}
			
			// 다음 시작 집과 끝 집으로 이동하기
			startHouse++;
			endHouse--;
		}
		
		int result = memo[memo.length - 1][0];
		for (int i = 1; i < memo[0].length; i++) {
			if (memo[maxDepth][i] < result) {
				result = memo[maxDepth][i];
			}
		}
		
		System.out.println(result);
	}

}
