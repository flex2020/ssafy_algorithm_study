package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1520 {
    static int N, M, answer;
    static int[][] board, visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1; // 방문배열 초기상태 -1로 세팅
            }
            
        }
        visited[N-1][M-1] = 1;
        System.out.println(dfs(0, 0));
    }
    public static int dfs(int r, int c) {
        if (r == N-1 && c == M-1) {
            return 1;
        }
        if (visited[r][c] != -1) {
            return visited[r][c];
        }
        visited[r][c] = 0;
        for (int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] < board[r][c]) {
                visited[r][c] += dfs(nr, nc);
            }
        }
        return visited[r][c];
    }
}