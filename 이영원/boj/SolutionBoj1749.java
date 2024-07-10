import java.io.*;
import java.util.*;


public class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][M]; // 행렬
        int[][] dp = new int[N][M]; // dp?
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 4중 for문이 된다 하하하하
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                dp[x][y] = matrix[x][y];
                answer = Math.max(answer, dp[x][y]);

                for (int i = y+1; i < M; i++) { // 위쪽 가생이 채우기
                    dp[x][i] = dp[x][i-1] + matrix[x][i];
                    answer = Math.max(answer, dp[x][i]);
                }

                for (int i = x+1; i < N; i++) { // 왼쪽 가생이 채우기
                    dp[i][y] = dp[i-1][y] + matrix[i][y];
                    answer = Math.max(answer, dp[i][y]);
                }

                // 죄다 도라버리기
                for (int i = x+1; i < N; i++) {
                    for (int j = y+1; j < M; j++) {
                        dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
                        answer = Math.max(answer, dp[i][j]);
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
