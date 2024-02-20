package 강윤서.swea;
import java.util.*;
import java.io.*;
public class Solution_1953 {
    static int T, N, M, R, C, L, answer;
    static int[][] board, time;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1}; // 상 하 좌 우
    // direction 배열을 통해 지하터널 통로 별로 이동할 수 있는 방향 관리
    static int[][] direction = {{0}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            board = new int[N][M];
            visited = new boolean[N][M];
            time = new int[N][M];
            for (int i=0; i<N; i++) {
                Arrays.fill(time[i], Integer.MAX_VALUE);
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<M; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // bfs();
            dfs(R, C, 1);
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
    public static void bfs() {
        Queue<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[] {R, C, 1});
        visited[R][C] = true;
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            if (cur[2] <= L) {
                answer++;
            }
            int type = board[cur[0]][cur[1]]; // 터널의 종류
            for (int i=0; i<direction[type].length; i++) {
                int nr = cur[0] + dr[direction[type][i]];
                int nc = cur[1] + dc[direction[type][i]];
                if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] != 0 && !visited[nr][nc] && cur[2]+1 <= L) {
                    int nextType = board[nr][nc];
                    for (int j=0; j<direction[nextType].length; j++) {
                        if (direction[type][i] % 2 == 0) {
                            if (direction[nextType][j] == direction[type][i] + 1) {
                                Q.offer(new int[] {nr, nc, cur[2]+1});
                                visited[nr][nc] = true;
                            }
                        } else {
                            if (direction[nextType][j] == direction[type][i] - 1) {
                                Q.offer(new int[] {nr, nc, cur[2]+1});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void dfs(int r, int c, int t) {
        if (time[r][c] != Integer.MAX_VALUE) {
            answer++;
        }
        time[r][c] = t;
        int type = board[r][c];
        for (int i=0; i<direction[type].length; i++) {
            int nr = r + dr[direction[type][i]];
            int nc = c + dc[direction[type][i]];
            if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] != 0 && t+1 < time[nr][nc] && t+1 <= L) {
                int nextType = board[nr][nc];
                for (int j=0; j<direction[nextType].length; j++) {
                    if (direction[type][i] % 2 == 0) {
                        if (direction[nextType][j] == direction[type][i] + 1) {
                            dfs(nr, nc, t+1);
                        }
                    } else {
                        if (direction[nextType][j] == direction[type][i] - 1) {
                            dfs(nr, nc, t+1);
                        }
                    }
                }
            }
        }
    }
}
