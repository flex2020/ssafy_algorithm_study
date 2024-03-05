package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Set<Integer> S = new HashSet<>(); // 중복제거
        List<Integer> coin = new ArrayList<>();
        for (int i=0; i<N; i++) {
            S.add(Integer.parseInt(br.readLine()));
        }
        for (Integer s : S) coin.add(s);
        Collections.sort(coin);
        int[] dp = new int[K+1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;
        for (int i=0; i<coin.size(); i++) {
            Integer cur = coin.get(i);
            if (cur > K) continue; // 인덱스 에러 방지
            dp[cur] = 1;
            for (int j=cur+1; j<=K; j++) {
                dp[j] = Math.min(dp[j], dp[j-cur] + dp[cur]);
            }
        }
        System.out.println(dp[K]==987654321 ? -1 : dp[K]);
    }
}
