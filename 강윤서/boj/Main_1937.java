import java.io.*;
import java.util.*;
public class Main {
    static int N, answer = 1;
    static int[][] board, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                dp[i][j] = Math.max(dp[i][j], dfs(i, j, board[i][j]));
//                print();
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }
    public static void print() {
        System.out.println("=============");
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int dfs(int r, int c, int value) {
        if (dp[r][c] != -1) return dp[r][c];
        for (int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (0 <= nr && nr < N && 0 <= nc && nc < N && board[nr][nc] > value) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc, board[nr][nc]) + 1);
            }
        }
        if (dp[r][c] == -1) return dp[r][c] = 1;
        else return dp[r][c];
    }

}
