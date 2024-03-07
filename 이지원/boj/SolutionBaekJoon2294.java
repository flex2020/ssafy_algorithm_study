import java.util.*;
import java.io.*;

public class SolutionBaekJoon2294 {

    static int k;
    static int[] coins;
    static int[] memo;
    static int minCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 동전 가치 입력받기
        coins = new int[n];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // 동전의 최소 개수 구하기
        memo = new int[k + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        minCount = Integer.MAX_VALUE;
        calculateMinCount(0, 0, 0);

        System.out.println(minCount == Integer.MAX_VALUE ? -1 : minCount);
    }

    public static void calculateMinCount(int index, int sum, int count) {
        // 동전 가치가 k인 경우
        if (sum == k) {
            minCount = Math.min(minCount, count);
            return;
        }

        // 이미 최소 개수를 넘은 경우
        if (count + 1 > minCount) {
            return;
        }

        for (int i = index; i < coins.length; i++) {
            int nextSum = sum + coins[i];

            if (nextSum > k || memo[nextSum] < count + 1) { // 이전 동전 개수가 더 적거나 목표 금액보다 큰 경우
                continue;
            }

            if (memo[nextSum] == Integer.MAX_VALUE || memo[nextSum] > count + 1) { // 이전이 없거나 더 동전 개수가 많은 경우
                memo[nextSum] = count + 1;
                calculateMinCount(i, nextSum, count + 1);
            }
        }
    }

}
