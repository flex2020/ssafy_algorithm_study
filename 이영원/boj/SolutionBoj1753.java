import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge{
		int num;
		int weight;
		public Edge(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [num=" + num + ", weight=" + weight + "]";
		}
	}
	
	static ArrayList<Edge>[] adjList;
	static int V, E, K;
	static int[] D;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken()); // 정점 수
		E = Integer.parseInt(st.nextToken()); // 간선 수
		K = Integer.parseInt(br.readLine()); // 시작 정점 번호
		
		adjList = new ArrayList[V+1];
		
		for (int i = 0; i < V+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Edge(to, weight));
			// 같은게 몇개가 들어와도 어짜피 pq에서 걸러진다.
		}
		
		dijkstra();
		
		System.out.println(sb);
		
	}
	
	private static void dijkstra() {
		// 가중치 기준 pq
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
			return a.weight-b.weight;
		});
		
		boolean[] visited = new boolean[V+1]; // 방문체크배열
		D = new int[V+1]; // 최단경로배열
		
		Arrays.fill(D, Integer.MAX_VALUE); // 초기화
		
		pq.offer(new Edge(K, 0)); // 시작정점(시작, 0)
//		D[K]=0;
		while(!pq.isEmpty()) {
			
			// 일반적인 경우에는 최솟값을 뽑는 코드가 있다.(그래서 pq가 빠르다.)
			
			Edge e = pq.poll();
			
			if(D[e.num]!=Integer.MAX_VALUE) continue; //최소값을 구한 경우 넘어감(방문체크를 겸하므로 visited 불필요)

			D[e.num] = e.weight; // 최소값 세팅(방문했다는 의미)
			
			for(Edge tmpE : adjList[e.num]) {
				int cost = D[e.num] + tmpE.weight;
				if(cost < D[tmpE.num]) { // 경유지보다 더 짧으면 pq에 넣어준다.(pq에서 빠지면 업데이트)
					pq.offer(new Edge(tmpE.num, cost));
				}
			}
		}
		
		for (int i = 1; i < V+1; i++) {
			if(D[i]==Integer.MAX_VALUE) {
				sb.append("INF\n");
			}else {
				sb.append(D[i]).append("\n");
			}
		}
	}

}
