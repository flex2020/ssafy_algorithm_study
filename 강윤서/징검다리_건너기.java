import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<int[]> DQ = new ArrayDeque<>();
        for (int i=0; i<stones.length; i++) { // 슬라이딩 윈도우의 end
            while (!DQ.isEmpty() && stones[i] > DQ.peekLast()[1]) {
                DQ.pollLast();
            }
            DQ.offerLast(new int[] {i, stones[i]});
            if (DQ.peekFirst()[0] <= i-k) { // 벗어남
                DQ.pollFirst();
            }
            if (i >= k-1) {
                answer = Math.min(answer, DQ.peekFirst()[1]);
            }
        }
        return answer;
    }
}
