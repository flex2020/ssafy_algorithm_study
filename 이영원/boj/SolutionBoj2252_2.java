import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] check = new boolean[N+1]; // 비교하지 않은 체크배열
		List<List<Integer>> adjList = new ArrayList<>();
		int[][] compare = new int[M][2]; // 입력받는 비교배열
		int[] degree = new int[N+1]; // 진입차수 배열
		
		for (int i = 0; i < N+1; i++) {
			adjList.add(new ArrayList<>());
		}
			
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			degree[to]++;
		}
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		// 진입차수가 0인 것들을 큐에 삽입(없으면 위상정렬 불가)
		for (int i = 1; i < degree.length; i++) {
			if(degree[i]==0) {
				dq.offerLast(i);
				check[i]=true;
			}
		}
		
		while(!dq.isEmpty()) {
			int cur = dq.pollFirst();
//			System.out.println(Arrays.toString(degree));
			
			sb.append(cur).append(" ");
			
			for(int i : adjList.get(cur)) {
				degree[i]--; // 내리고 0되면 포함
				if(degree[i]==0) {
					dq.offerLast(i);
				}
			}
		}
		
		System.out.println(sb);
		
	}
	
}
