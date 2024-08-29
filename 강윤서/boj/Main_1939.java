package solution;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]> edges;
    static int[] visited, startIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        visited = new int[N + 1];
        startIdx = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new int[] {a, b, c});
            edges.add(new int[] {b, a, c});
        }

        Collections.sort(edges, (e1, e2) -> e1[0] - e2[0]);
        Arrays.fill(startIdx, -1);
        for (int i = 0; i < edges.size(); i++) {
            if (startIdx[edges.get(i)[0]] == -1) {
                startIdx[edges.get(i)[0]] = i;
            }
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p2[1] - p1[1]);
        for (int i=startIdx[start]; ; i++) {
            if (i>=edges.size() || edges.get(i)[0] != start) break;
            PQ.offer(new int[] {edges.get(i)[1], edges.get(i)[2], edges.get(i)[2]});
            visited[edges.get(i)[1]] = Math.max(visited[edges.get(i)[1]], edges.get(i)[2]);
        }
        visited[start] = Integer.MAX_VALUE;
        int answer = 0;

        while (!PQ.isEmpty()) {
            int[] cur = PQ.poll();
            if (cur[0] == end) {
                answer = Math.max(answer, cur[1]);
                break;
            }
            for (int i=startIdx[cur[0]]; ; i++) {
                if (i>=edges.size() || edges.get(i)[0] != cur[0]) break;
                if (visited[edges.get(i)[1]] < edges.get(i)[2]) {
                    PQ.offer(new int[] {edges.get(i)[1], Math.min(edges.get(i)[2], cur[1]), edges.get(i)[2]});
                    visited[edges.get(i)[1]] = edges.get(i)[2];
                }
            }
        }
        System.out.println(answer);
    }
}
