package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int answer = 0;
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] board = new int[N+1][N+1];
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                board[from][to] = 1;
                board[to][from] = 1;
            }
            boolean[] visited = new boolean[N+1];
            Queue<Integer> Q = new ArrayDeque<>(); // 가중치 X -> PQ 대신 Q
            Q.offer(1);
            while (!Q.isEmpty()) {
                int cur = Q.poll();
                if (visited[cur]) continue;
                visited[cur] = true;
                answer++;
                for (int i=1; i<N+1; i++) {
                    if (board[cur][i] != 0 && !visited[i]) {
                        Q.offer(i);
                    }
                }
            }
            System.out.println(answer-1); // 시작 빼기
        }
    }
}
