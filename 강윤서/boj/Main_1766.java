import java.io.*;
import java.util.*;
public class Main {
    static int N, M, idx;
    static int[] indegree, answer;
    static boolean[] visited;
    static List<Integer>[] board;
    static PriorityQueue<Integer> PQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];
        answer = new int[N];
        visited = new boolean[N+1];
        board = new List[N+1];
        PQ = new PriorityQueue<>();

        for (int i=0; i<=N; i++) board[i] = new ArrayList<>();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            indegree[e]++;
            board[s].add(e);
        }
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) {
                PQ.offer(i);
                visited[i] = true;
            }
        }
        while (!PQ.isEmpty()) {
            int value = PQ.poll();
            answer[idx++] = value;
            for (int next : board[value]) {
                indegree[next]--;
                if (indegree[next] == 0 && !visited[next]) {
                    PQ.offer(next);
                    visited[next] = true;
                }
            }
        }
        for (int i=1; i<=N; i++) {
            if (!visited[i]) {
                answer[idx++] = i;
            }
        }
        for (int i=0; i<N; i++) sb.append(answer[i] + " ");
        System.out.println(sb);
    }
}
