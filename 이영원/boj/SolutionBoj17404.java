import java.io.*;
import java.util.*;

public class Main {

    static int answer, N, start;

    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 집 수
        answer = Integer.MAX_VALUE;
        map = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }


        if(N>=3){ // 3이상인 우

            for (int i = 0; i < 3; i++) { // 처음 고정
                for (int j = 0; j <3; j++) { // 끝 고정
                    if(i==j) continue;
                    dp[0][i]=map[0][i];
                    dp[0][(i+1)%3] = 10000;
                    dp[0][(i+2)%3] = 10000;

                    for (int k = 1; k < N; k++) { // 돌리기
                        for (int l = 0; l < 3; l++) {
                            int idx1 = l-1<0 ? 2 : l-1;
                            int idx2 = l+1>2 ? 0 : l+1;
                            dp[k][l] = Math.min(dp[k-1][idx1]+map[k][l], dp[k-1][idx2]+map[k][l]);
                        }
                    }
                    answer = Math.min(answer, dp[N-1][j]);
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(i==j) continue;
                    answer = Math.min(answer, map[0][i]+map[1][j]);
                }
            }
        }

        System.out.println(answer);
    }
}
