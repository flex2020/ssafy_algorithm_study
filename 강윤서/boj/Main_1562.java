import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][10][1 << 10];
        for (int i = 1; i < 10; i++) {
            dp[0][i][1 << i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int bit = 0; bit < 1024; bit++) {
                    if (j >= 1) {
                        dp[i][j][bit | (1 << j)] += dp[i - 1][j - 1][bit];
                    }
                    if (j <= 8) {
                        dp[i][j][bit | (1 << j)] += dp[i - 1][j + 1][bit];
                    }
                    dp[i][j][bit | (1 << j)] %= 1000000000;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N-1][i][(1 << 10) - 1];
            answer %= 1000000000;
        }
        System.out.println(answer);
    }
}
