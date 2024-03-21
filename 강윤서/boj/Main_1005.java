package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            List<List<Integer>> list = new ArrayList<>();
            int[] D = new int[N+1];
            int[] indegrees = new int[N+1];
            int[] dp = new int[N+1];
            Queue<Integer> Q = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<=N; i++) {
                list.add(new ArrayList<>());
            }
            for (int i=1; i<=N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.get(a).add(b);
                indegrees[b] += 1;
            }
            int W = Integer.parseInt(br.readLine());
            for (int i=1; i<=N; i++) {
                if (indegrees[i] == 0) {
                    Q.offer(i);
                    dp[i] = D[i];
                }
            }
            while (!Q.isEmpty()) {
                int cur = Q.poll();
                for (int next : list.get(cur)) {
                    indegrees[next]--; // 진입차수 감소
                    dp[next] = Math.max(dp[next], dp[cur] + D[next]);
                    if (indegrees[next] == 0) {
                        Q.offer(next);
                    }
                }
            }
            sb.append(dp[W] + "\n");
        }
        System.out.println(sb);
    }
}
