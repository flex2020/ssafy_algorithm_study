import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        PriorityQueue<int[]> PQ = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        PriorityQueue<int[]> ready = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]);
        for (int[] job : jobs) {
            ready.offer(job);
        }
        while (!PQ.isEmpty() || !ready.isEmpty()) {
            while (!ready.isEmpty() && ready.peek()[0] <= time) {
                PQ.offer(ready.poll());
            }
            if (PQ.isEmpty()) { // 현재 가능한 작업 X
                time = ready.peek()[0];
            } else {
                int[] cur = PQ.poll();
                answer += time + cur[1] - cur[0];
                time += cur[1];
            }
        }
        return answer/jobs.length;
    }
}
