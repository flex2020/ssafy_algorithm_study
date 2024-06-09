import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        long goal = 0;
        int maxCnt = 600000;
        Queue<Integer> Q1 = new ArrayDeque<>();
        Queue<Integer> Q2 = new ArrayDeque<>();
        for (int i=0; i<queue1.length; i++) {
            sum1 += queue1[i];
            Q1.offer(queue1[i]);
        }
        for (int i=0; i<queue2.length; i++) {
            sum2 += queue2[i];
            Q2.offer(queue2[i]);
        }
        if ((sum1 + sum2) % 2 == 1) return -1; // 홀수는 불가능

        goal = (sum1 + sum2) / 2;
        int answer = 0;
        while (true) {
            if (Q1.isEmpty() || Q2.isEmpty()) return -1;
            if (answer > maxCnt) return -1;
            if (sum1 == goal) {
                break;
            } else if (sum1 > goal) { // Q1에서 빼기
                int value = Q1.poll();
                sum1 -= value;
                Q2.offer(value);
                answer++;
            } else { // Q2에서 가져오기
                int value = Q2.poll();
                sum1 += value;
                Q1.offer(value);
                answer++;
            }
        }
        return answer;
    }
}
