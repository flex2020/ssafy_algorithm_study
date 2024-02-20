package 강윤서.boj;

import java.util.*;
import java.io.*;
public class Main_17070 {
	static int N, answer;
	static int[][] board, direction = { {0, 1}, {0, 1, 2}, {1, 2} }; // 0: 오른쪽, 1: 대각선, 2: 아래;
	static int[] dr = {0, 1, 1}, dc = {1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        if (board[N-1][N-1] == 1) {
            answer = 0;
        } else {
            dfs(0, 0, 0);
        }
		System.out.println(answer);
	}
    public static void dfs(int r, int c, int d) {
        if (r + dr[d] == N-1 && c + dc[d] == N-1) { // 헤드가 목표 지점에 도착
            answer++;
            return ;
        }
        // 밀기 -> 헤드 기준으로 가능한지 체크
        for (int i=0; i<direction[d].length; i++) {
            // nr, nc: 파이프의 헤드를 놓아도 되는지 확인하기 위한 다음 좌표 (파이프를 밀 수 있느냐)
            int nr = r + dr[d] + dr[direction[d][i]];
            int nc = c + dc[d] + dc[direction[d][i]];
            if (0<=nr && nr<N && 0<=nc && nc<N) {
                if (direction[d][i] == 0 || direction[d][i] == 2) { // 오른쪽, 아래 -> 한 칸만 확인
                    if (board[nr][nc] == 0) {
                        dfs(r + dr[d], c + dc[d], direction[d][i]);
                    }
                } else if (direction[d][i] == 1) { // 대각선 -> 세 칸 확인
                    if (board[nr][nc] == 0 && board[nr][nc-1] == 0 && board[nr-1][nc] == 0) {
                        dfs(r + dr[d], c + dc[d], direction[d][i]);
                    }
                }
            }
        }
    }
}
