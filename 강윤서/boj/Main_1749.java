package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1749 {
    static int N, M;
    static int[][] board, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1]; // sum[i][j] : (0, 0) ~ (i, j) 까지의 합
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] + board[i][j] - sum[i - 1][j - 1];
            }
        }
        
        int answer = Integer.MIN_VALUE; // 0 아닌 거 주의 ! 
        for (int startR = 1; startR <= N; startR++) {
            for (int startC = 1; startC <= M; startC++) {
                for (int endR = startR; endR <= N; endR++) {
                    for (int endC = startC; endC <= M; endC++) {
                        int value = sum[endR][endC] - sum[startR - 1][endC] - sum[endR][startC - 1] + sum[startR - 1][startC - 1];
                        answer = Math.max(answer, value);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
