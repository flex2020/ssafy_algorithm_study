package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_16928 {
    static int N, M, answer;
    static int[] board;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        board = new int[101];
        visited = new boolean[101];
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0; i<N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            board[from] = to;
        }
        Queue<int[]> Q = new ArrayDeque<>(); 
        Q.offer(new int[] {1, 0}); // location, cnt
        visited[1] = true;
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            if (cur[0] == 100) {
                answer = cur[1];
                break;
            }
            for (int i=1; i<=6; i++) { // 주사위
                if (cur[0] + i > 100 || visited[cur[0] + i]) continue;
                if (board[cur[0]+i] == 0) { // 사다리나 뱀 연결 X
                    Q.offer(new int[] {cur[0]+i, cur[1]+1});
                    visited[cur[0]+i] = true;
                } else {
                    Q.offer(new int[] {board[cur[0]+i], cur[1]+1});
                    visited[board[cur[0]]] = true;
                }
            }
        }
        System.out.println(answer);
    }
}
