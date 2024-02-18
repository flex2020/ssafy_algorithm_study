import java.util.*;
import java.io.*;

class Vertex {
	int value;
	Vertex to;
	
	Vertex(int value, Vertex to) {
		this.value = value;
		this.to = to;
	}
}

public class SolutionBaekJoon2252 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Vertex[] graph = new Vertex[N + 1];
		int[] edgeCounts = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int shorter = Integer.parseInt(st.nextToken());
			int longer = Integer.parseInt(st.nextToken());
			graph[shorter] = new Vertex(longer, graph[shorter]);
			edgeCounts[longer]++;
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Vertex> queue = new ArrayDeque<>();
        
		// 차수가 0인 정점 찾기
		for (int i = 1; i < edgeCounts.length; i++) {
			if (edgeCounts[i] == 0) {
				sb.append(i + " ");  // 줄 세우기
				if (graph[i] != null) {
					queue.offer(graph[i]);
				}
				edgeCounts[i] = -1;
			}
		}
		
		while (!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			
			// 인접 정점 차수 줄이기
			while (vertex != null) {
				edgeCounts[vertex.value]--;
				vertex = vertex.to;
			}
			
			// 차수 0인 정점 찾기
			for (int i = 1; i < edgeCounts.length; i++) {
				if (edgeCounts[i] == 0) {
					// 줄 세우기
					sb.append(i + " ");
					if (graph[i] != null) {
						queue.offer(graph[i]);
					}
					edgeCounts[i] = -1;
				}
			}
		}
		
		System.out.println(sb);
	}

}
