package 강윤서.boj;

import java.util.*;
import java.io.*;
public class Main_10451 {
    static int T, N, answer;
    static int[] A;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            A = new int[N+1];
            visited = new boolean[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++) {
                A[i+1] = Integer.parseInt(st.nextToken());
            }
            for (int i=1; i<=N; i++) {
                if (!visited[i]) {
                    union(i);
                    answer++;
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
    public static void union(int root) {
        if (visited[root]) return ;
        visited[root] = true;
        union(A[root]);
    }
}
