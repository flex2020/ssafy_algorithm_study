import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<List<Integer>> adjList;
	static int[][] mList;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>(); // 인접리스트
		mList = new int[M][2]; // 입력받은 것들 배열
		visited = new boolean[N]; // 방문체크배열
		
		int answer=0;
		
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new  StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
			mList[i][0]=from;
			mList[i][1]=to;
		}
		
		for (int i = 0; i < M; i++) {
//			System.out.println(mList[i][0] + " " + mList[i][1]);
			visited[mList[i][0]]=true;
			visited[mList[i][1]]=true;
			// from -> to와 to -> from으로 시작하는 양쪽을 생각해주는 것이다.
			if(tagogagi(adjList.get(mList[i][1]), mList[i][0], mList[i][1], 1) || tagogagi(adjList.get(mList[i][0]), mList[i][1], mList[i][0], 1)) {
				answer=1;
				break;
			}
			visited[mList[i][0]]=false;
			visited[mList[i][1]]=false;
		}
		
		System.out.println(answer);
		
	}

	// 해당 포인트(to)의 인접리스트와 from, to, cnt를 보고 cnt가 4가 되면 리턴, 중간중간 방문체크해주고 원복해주고 해줘야한다.
	private static boolean tagogagi(List<Integer> adjs, int from, int to, int cnt) {
		// basis part
		if(cnt==4) {
			return true;
		}
		// inductive part
		for(int a : adjs) {
			if(a!= from && a!= to && !visited[a]) {
				visited[a]=true;
//				System.out.println(num2 + " " + a + " " + num1);
				if(tagogagi(adjList.get(a), to, a, cnt+1)) {
					return true;
				}
				visited[a]=false;
			}
		}
		return false;
	}
	
}
