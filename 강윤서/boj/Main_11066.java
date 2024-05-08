package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N + 1];
            int[] sum = new int[N + 1];
            int[][] dp = new int[N + 1][N + 1]; // dp[i][j] : i번부터 j까지의 합의 최솟값
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + A[i]; // 누적합 계산
            }
            for (int i = 1; i <= N; i++) { // 개수
                for (int from = 1; from + i <= N; from++) {
                    int to = from + i; // dp[from][to] 구하기
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            System.out.println(dp[1][N]);
        }
    }
}
