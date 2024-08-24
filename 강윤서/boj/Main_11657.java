import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static List<int[]> edges;
    static long[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        time = new long[N+1]; // 1번부터 i까지 도달하는데 걸리는 시간
        for (int i=1; i<=N; i++) {
            time[i] = Long.MAX_VALUE;
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges.add(new int[] {s, e, t});
        }
        time[1] = 0;
        for (int round=0; round<N; round++) {
            for (int i=0; i<M; i++) {
                if (time[edges.get(i)[0]] == Long.MAX_VALUE) continue;
                if (time[edges.get(i)[0]] + edges.get(i)[2] < time[edges.get(i)[1]]) {
                    time[edges.get(i)[1]] = time[edges.get(i)[0]] + edges.get(i)[2];
                    if (round == N-1) { // 음의 사이클 존재
                        System.out.println(-1);
                        return ;
                    }
                }
            }
        }

        for (int i=2; i<=N; i++) {
            if (time[i] == Long.MAX_VALUE) time[i] = -1;
            System.out.println(time[i]);
        }
    }
}
