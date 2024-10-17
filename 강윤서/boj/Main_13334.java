import java.io.*;
import java.util.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> PQ1 = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]); // 시작시간 기준 정렬
        PriorityQueue<int[]> PQ2 = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]); // 종료 시간 기준 정렬
        PriorityQueue<int[]> PQ3 = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]); // 현재 철도 안에 들어있는거
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a < b) {
                PQ1.offer(new int[] {a, b});
                PQ2.offer(new int[] {a, b});
            } else {
                PQ1.offer(new int[] {b, a});
                PQ2.offer(new int[] {b, a});
            }
        }
        int d = Integer.parseInt(br.readLine());
        int answer = 0;
        Queue<int[]> Q = new ArrayDeque<>();
        while (!PQ1.isEmpty()) {
            int[] cur = PQ1.poll();
            if (cur[1] - cur[0] > d) continue;
            while (!PQ3.isEmpty()) {
                int[] cur2 = PQ3.poll();
                if (cur2[0] < cur[0]) continue;
                else {
                    PQ3.offer(cur2);
                    break;
                }
            }
            while (!PQ2.isEmpty()) {
                int[] next = PQ2.poll();
                if (next[1] - next[0] > d) continue;
                if (next[0] < cur[0]) continue;
                if (next[1] > cur[0] + d) { // 철로를 벗어날 때
                    PQ2.offer(next);
                    break;
                }
                PQ3.offer(next);
            }
            answer = Math.max(answer, PQ3.size());
        }
        System.out.println(answer);
    }
}
