package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int answer = Integer.MAX_VALUE;
        int[][] table = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int start=0; start<3; start++) { // 첫 집 색 고정
            int[][] dp = new int[N][3];
            for (int i=0; i<3; i++) {
                if (start == i) dp[0][i] = table[0][i];
                else dp[0][i] = 10000000;
            }
            for (int i = 1; i < N; i++) { // 그 다음 집 색칠해보기
                for (int j = 0; j < 3; j++) { // i 번째 집을 j의 색으로 칠하는 최소비용
                    dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + table[i][j];
                }
            }
            for (int i = 0; i < 3; i++) {
                if (i == start) continue; // 첫 집 색과 동일
                answer = Math.min(answer, dp[N - 1][i]);
            }
        }

        System.out.println(answer);
    }
}
