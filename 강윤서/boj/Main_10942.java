import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static int[] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        board = new int[N+1];
        dp = new int[N + 1][N+1]; // dp[i][j] : i ~ j 팰린드롬 여부

        for (int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }
        for (int j=1; j<=N; j++)  { // 끝
            for (int i = 1; i<N; i++) { // 시작
                if (i >= j) continue;
                if (board[i] == board[j]) {
                    if (i+1 >= j-1)
                        dp[i][j] = 1;
                    else if (dp[i+1][j-1] == 1)
                        dp[i][j] = 1;
                }
            }
        }
//        print();
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e] + "\n");
        }
        System.out.println(sb);
    }
    public static void print() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
