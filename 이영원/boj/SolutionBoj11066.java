import java.io.*;
import java.util.*;



public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            int answer=0;

            int[][] dp = new int[K+1][K+1]; // dp
            int[][] sum = new int[K+1][K+1]; // 합

            for (int i = 0; i < K+1; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE); // 위쪽 삼각형 max로 채우기
                dp[i][i]=0; // 같은건 0으로
            }

            st = new StringTokenizer(br.readLine());
            // dp랑 sum에 0 행에 입력받은거 넣기
            for (int i = 1; i <= K; i++) {
                dp[0][i]=Integer.parseInt(st.nextToken());
                sum[0][i] = dp[0][i];
            }

            // dp랑 sum에 둘이 더한거 넣기
            for (int i = 1; i < K; i++) {
                dp[i][i+1] = dp[0][i]+dp[0][i+1];
                sum[i][i+1] = dp[i][i+1];
            }

            // dp[j][j+i]라면 j부터 j+i 범위의 수로 만든 합중 최소치
            for (int i = 2; i <= K; i++) { // i는 얼만큼 차이나는지
                for (int j = 1; j+i <= K; j++) { // j는 시작점
                    sum[j][j+i] = sum[j][j+i-1] + dp[0][j+i]; // sum[j][j+i]는 j부터 j+i 범위의 수의 합
                    // dp[1][4]는
                    // dp[1][1] + dp[2][4] + sum[1][4]
                    // dp[1][2] + dp[3][4] + sum[1][4]
                    // dp[1][3] + dp[4][4] + sum[1][4] 중 최솟값이다.
                    for (int k = j; k < j+i; k++) {
                        dp[j][j+i]=Math.min(dp[j][j+i], dp[j][k]+dp[k+1][j+i]+sum[j][j+i]);
                    }
                }
            }
//            print(sum);
//            print(dp);
            answer=dp[1][K]; // 그렇다.
            sb.append(answer).append("\n");

        }
        System.out.println(sb);
    }

    private static void print(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
