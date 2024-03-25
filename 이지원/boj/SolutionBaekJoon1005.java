import java.util.*;
import java.io.*;

public class SolutionBaekJoon1005 {
	
	static int N;
	static int[] weights;
	static List<List<Integer>> graph;
	static int[] counts;
	static int W;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			weights = new int[N + 1];
			counts = new int[N + 1];
			
			// 그래프 생성하기
			graph = new ArrayList<>();
			for (int n = 0; n <= N; n++) {
				graph.add(new ArrayList<>());
			}
			
			// 건설 시간 입력받기
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				weights[j] = Integer.parseInt(st.nextToken());
			}
			
			// 간선 입력받기
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				counts[to]++;
			}
			
			// 목표 지점 입력받기
			W = Integer.parseInt(br.readLine());
			
			// 최소 시간 구하기
			System.out.println(calculateResult());
		}
	}
	
	public static int calculateResult() {
		Queue<Integer> queue = new ArrayDeque<>();
		int[] finalWeights = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			finalWeights[i] = weights[i];
		}
		
		// 출발지(진입 차수 = 0)인 곳 찾기
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] == 0) {
				if (i == W) {  // 목표 지점인 경우
					return weights[W];
				}
				counts[i] = -1;
				queue.offer(i);
			}
		}
		
		aa: while (!queue.isEmpty()) {
			int from = queue.poll();

			// 진입 차수가 0인 곳과 연결된 곳의 진입 차수 줄이기
			for (int to : graph.get(from)) {
				if (counts[to] > 0) {
					counts[to]--;
					
					// 가중치 최댓값으로 갱신하기
					finalWeights[to] = Math.max(finalWeights[to], weights[to] + finalWeights[from]);
				}
			}
			
			// 진입 차수가 0인 곳 찾기
			for (int i = 1; i < counts.length; i++) {
				if (counts[i] == 0) {
					if (i == W) {  // 목표 지점인 경우
						break aa;
					}
					counts[i] = -1;
					queue.offer(i);
				}
			}
		}
		
		return finalWeights[W];
	}

}
