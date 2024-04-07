package 강윤서.programmers;

import java.util.*;

class Solution {
    static int N; // user 수
    static int M; // 이모티콘 개수
    static int[] discountRate = {40, 30, 20, 10};
    static int[] emoti;
    static PriorityQueue<int[]> answer;
    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        emoti = new int[M];
        answer = new PriorityQueue<int[]>((p1, p2) -> p1[0] == p2[0] ? p2[1] - p1[1] : p2[0] - p1[0]);
        setDiscountRate(0, users, emoticons);
        return answer.poll();
    }
    public static void setDiscountRate(int cnt, int[][] users, int[] emoticons) {
        if (cnt == M) {
            // 모든 이모티콘에 대해 할인율 계산 완료
            calculate(users, emoticons);
            return ;
        }
        for (int i=0; i<4; i++) {
            emoti[cnt] = discountRate[i];
            setDiscountRate(cnt+1, users, emoticons);
        }
    }
    public static void calculate(int[][] users, int[] emoticons) {
        int people = 0; // 이모티콘 플러스 가입 명수
        int profit = 0; // 이모티콘 판매액
        for (int[] u : users) {
            int price = 0;
            for (int i=0; i<M; i++) {
                if (emoti[i] < u[0]) continue; // 사지 않을 이모티콘
                price += emoticons[i] / 100 * (100-emoti[i]);
            }
            if (price >= u[1]) {
                people += 1;
            } else {
                profit += price;
            }
        }
        answer.offer(new int[] {people, profit});
    }
}