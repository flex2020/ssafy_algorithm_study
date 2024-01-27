import java.util.*;
import java.io.*;

public class SolutionSWEA7236 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 위왼, 위, 위오, 왼, 오, 아왼, 아, 아오
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            String[][] inputs = new String[N + 2][N + 2];
            Arrays.fill(inputs[0], "G");
            Arrays.fill(inputs[inputs.length - 1], "G");
            
            StringTokenizer st;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                inputs[i][0] = "G";
                inputs[i][inputs[0].length - 1] = "G";
                for (int j = 1; j <= N; j++) {
                    inputs[i][j] = st.nextToken();
                }
            }

            int maxSum = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int sum = 0;
                    for (int k = 0; k < dx.length; k++) {
                        if (inputs[i + dx[k]][j + dy[k]].equals("W")) {
                            sum++;
                        }
                    }

                    if (sum == 0) {
                        sum++;
                    }

                    maxSum = Math.max(maxSum, sum);
                }
            }

            System.out.println("#" + testCase + " " + maxSum);
        }
    }
}