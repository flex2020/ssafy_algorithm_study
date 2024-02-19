import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {
	
	static List<List<Integer>> adjList; // 인접리스트
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int comNum = Integer.parseInt(br.readLine());
		int connectNum = Integer.parseInt(br.readLine());
		int answer=0;
		boolean[] visited = new boolean[comNum+1];
		visited[1]=true;
		
		adjList = new ArrayList<>();
		
		for (int i = 0; i < comNum+1; i++) {
			adjList.add(new ArrayList<>()); 
		}
		
		for (int i = 0; i < connectNum; i++) { // 양방향
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offerLast(1);
		while(!dq.isEmpty()) {
			int num = dq.pollFirst();
			for(int i : adjList.get(num)) {
				if(!visited[i]) {
					visited[i]=true;
					dq.offerLast(i);
					answer++;
				}
			}
		}
		
		System.out.println(answer);
		
		
	}
}
