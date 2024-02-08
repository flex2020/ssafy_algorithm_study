import java.util.*;
import java.io.*;

public class SolutionJungol1681 {
	
	static int[][] map;
	static boolean[] isVisited;
	static int minCost;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minCost = Integer.MAX_VALUE;
		isVisited = new boolean[map.length];
		isVisited[0] = true;
		dfs(0, 1, 0);
		
		System.out.println(minCost);
	}
	
    // current: 현재 장소
    // count: 방문한 장소 개수
    // cost: 현재까지의 이동경비
	public static void dfs(int current, int count, int cost) {
        // 모든 장소를 방문한 경우
		if (count == map.length) {
			if (map[current][0] != 0) {  // 다시 회사에 방문할 수 있는 경우
                // (현재까지의 이동경비 + 회사까지의 이동경비)와 현재 제일 작은 이동경비 중 최솟값 구하기
				minCost = Math.min(minCost, cost + map[current][0]);
			}
			return;
		}
		
		for (int j = 0; j < map.length; j++) {
            // 방문할 수 있는 장소의 조건
            // 1. 방문하지 않은 장소(!isVisited[j])
            // 2. 현재 장소에서 방문할 수 있는 장소(map[current][j] != 0)
            // 3. 현재까지의 이동경비 + 방문할 장소까지의 이동경비 < 현재 제일 작은 이동경비(cost + map[current][j] < minCost)
			if (!isVisited[j] && map[current][j] != 0 && cost + map[current][j] < minCost) {
				isVisited[j] = true;
				dfs(j, count + 1, cost + map[current][j]);
				isVisited[j] = false;
			}
		}
	}

}
