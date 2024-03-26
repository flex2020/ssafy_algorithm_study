package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_4485 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int[][] board = new int[N][N];
            int[][] visited = new int[N][N];
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
            int answer = Integer.MAX_VALUE;
            Queue<int[]> Q = new ArrayDeque<>();
            visited[0][0] = board[0][0];
            Q.offer(new int[] {0, 0, board[0][0]});
            while (!Q.isEmpty()) {
                int[] cur = Q.poll();
                for (int i=0; i<4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if (0<=nr && nr<N && 0<=nc && nc<N && visited[cur[0]][cur[1]] + board[nr][nc] < visited[nr][nc]) {
                        visited[nr][nc] = visited[cur[0]][cur[1]] + board[nr][nc];
                        Q.offer(new int[] {nr, nc});
                    }
                }
            }
            sb.append("Problem " + tc + ": " + visited[N-1][N-1] +"\n");
            tc++;
        }
        System.out.println(sb);
    }
}
