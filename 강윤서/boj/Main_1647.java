package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_1647 {
    static int N, M, answer, maxDist = Integer.MIN_VALUE;
    static List<List<int[]>> board;
    static boolean[] visited;
    static PriorityQueue<int[]> PQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList<>();
        for (int i=0; i<=N+1; i++) {
            board.add(new ArrayList<>());
        }
        visited = new boolean[N+1];
        PQ = new PriorityQueue<>(Comparator.comparingInt(p -> p[1]));
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board.get(a).add(new int[] {b, c});
            board.get(b).add(new int[] {a, c});
        }
        PQ.offer(new int[] {1, 0}); // 1번에서 시작
        while (!PQ.isEmpty()) {
            int[] cur = PQ.poll();
            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            answer += cur[1];
            maxDist = Math.max(maxDist, cur[1]);
            for (int[] next : board.get(cur[0])) {
                if (!visited[next[0]]) {
                    PQ.offer(next);
                }
            }
        }
        System.out.println(answer - maxDist);
    }
}
