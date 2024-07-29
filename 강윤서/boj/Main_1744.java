package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        PriorityQueue<Integer> positive = new PriorityQueue<>((p1, p2) -> p2 - p1);
        PriorityQueue<Integer> negative = new PriorityQueue<>(); // 0도 포함
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) positive.offer(num);
            else negative.offer(num);
        }
        while (!positive.isEmpty() || !negative.isEmpty()) {
            while (!positive.isEmpty()) {
                int cur = positive.poll();
                if (!positive.isEmpty()) {
                    if (positive.peek() * cur > positive.peek() + cur) { // 곱한 게 더 큼
                        answer += positive.peek() * cur;
                        positive.poll(); // 곱하는 거에 쓰인 애는 빼기
                    } else { // 더한 게 더 이득 -> 같다면 더하기 곱해서 더 높아질 수 있기 때문
                        answer += cur;
                    }
                    continue;
                }
                answer += cur;
            }
            while (!negative.isEmpty()) {
                int cur = negative.poll();
                if (!negative.isEmpty()) {
                    if (negative.peek() * cur > negative.peek() + cur) {
                        answer += negative.peek() * cur;
                        negative.poll();
                    } else {
                        answer += cur;
                    }
                } else {
                    answer += cur;
                }
            }
        }
        System.out.println(answer);
    }
}
