package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(p -> p[1]));
        List<List<int[]>> board = new ArrayList<>();
        boolean[] visited = new boolean[V+1];
        for (int i=0; i<=V; i++) {
            board.add(new ArrayList<>());
        }
        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board.get(a).add(new int[] {b, c});
            board.get(b).add(new int[] {a, c});
        }
        int answer = 0;
        PQ.offer(new int[] {1, 0});
        while (!PQ.isEmpty()) {
            int[] cur = PQ.poll();
            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            answer += cur[1];
            for (int[] next : board.get(cur[0])) {
                if (!visited[next[0]]) {
                    PQ.offer(next);
                }
            }
        }
        System.out.println(answer);
    }
}
