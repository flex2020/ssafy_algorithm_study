import java.io.*;
import java.util.*;

public class Main {
	static int[][] matrix;
	static boolean[] visited;
	static int answer;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		matrix = new int[N][N];
		visited = new boolean[N];
		answer=Integer.MAX_VALUE;

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				matrix[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		System.out.println(answer);

	}
	// 시작지점, 축적된 비용, 몇번 움직였는지
	private static void dfs(int start, int score, int cnt){
		if(answer<score) { // 현재 저장된 비용보다 적게 움직였는데 커지면 리턴
			return;
		}
			// 다시 회사로 돌아오는데 모든 곳을 돌고왔으면 더 작은걸 기록
		if(start==0 && cnt==N){
			answer = Math.min(answer, score);
			return;
		}

		// 뒤에서부터 돌긴했는데 큰 의미가 있었나 싶긴하다.
		for(int i=N-1;i>=0;i--){
			if(!visited[i] && matrix[start][i]!=0){
				visited[i]=true;
				dfs(i, score+matrix[start][i], cnt+1);
				visited[i]=false;
			}
		}
	}
}
