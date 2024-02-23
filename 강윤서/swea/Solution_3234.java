package 강윤서.swea;

import java.util.*;
import java.io.*;
public class Solution_3234 {
	static int T, N, answer;
	static int[] weight, selected;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			weight = new int[N];
			selected = new int[N];
			visited = new boolean[N]; // true: 오른쪽 / false: 왼쪽
			answer = 0;
			for (int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
            perm(0);
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
	public static void perm(int cnt) { // 순열로 순서 정하기
		if (cnt == N) {
			checkWeight(0, 0, 0);
			return ;
		}
		for (int i=0; i<N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[cnt] = i;
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}
    
	public static void checkWeight(int cnt, int left, int right) {
        if (cnt == N) {
            answer++;
            return ;
        }
        if (left >= right + weight[selected[cnt]]) {
            checkWeight(cnt+1, left, right+weight[selected[cnt]]); // 오른쪽에 올려보기
        }
        checkWeight(cnt+1, left+weight[selected[cnt]], right); // 왼쪽에 올려보기
	}
}
