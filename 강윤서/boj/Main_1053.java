import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static char[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        array = br.readLine().toCharArray();
        N = array.length;
        answer = makePalindrome(); // 4번 수행하지 않고 진행
        // 4번 연산 먼저 수행
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (array[i]== array[j]) continue;
                char ch = array[i];
                array[i] = array[j];
                array[j] = ch;
                int result = makePalindrome();
                answer = Math.min(answer, result + 1);
                array[j] = array[i];
                array[i] = ch;
            }
        }
        System.out.println(answer);
    }
    public static int makePalindrome() {
//        System.out.println(Arrays.toString(array));
        int[][] dp = new int[N][N];
        dp[0][0] = 0;
        dp[N-1][N-1] = 0;
        for (int i=0; i<N; i++) {
            for (int j=i; j<N; j++) {
                if (i == j) dp[i][j] = 0;
                else if (j == i+1) {
                    if (array[i] == array[j]) dp[i][j] = 0;
                    else dp[i][j] = 1;
                }
                else dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i=N-1; i>=0; i--) {
            for (int j=0; j<N; j++) {
                if (i > j) continue;
                if (i >= 1) {
                    dp[i-1][j] = Math.min(dp[i-1][j], dp[i][j] + 1);
                }
                if (j < N-1) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                if (i >= 1 && j < N-1) {
                    if (array[i-1] == array[j+1]) {
                        dp[i-1][j+1] = Math.min(dp[i-1][j+1], dp[i][j]);
                    } else {
                        dp[i-1][j+1] = Math.min(dp[i-1][j+1], dp[i][j] + 1);
                    }
                }
            }
        }
//        print(dp);
        return dp[0][N-1];
    }
    public static void print(int[][] dp) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
