import java.util.*;
import java.io.*;

public class SolutionBaekJoon1655 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> less = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> more = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        less.offer(n);
        sb.append(n + "\n");
        for (int i = 2; i <= N; i++) {
            n = Integer.parseInt(br.readLine());

            if (more.peek() != null && n > more.peek()) {
                more.offer(n);
            } else {
                less.offer(n);
            }

            if (i % 2 == 0) {
                if (less.size() > more.size()) {
                    more.offer(less.poll());
                } else if (less.size() < more.size()) {
                    less.offer(more.poll());
                }

                if (less.peek() < more.peek()) {
                    sb.append(less.peek() + "\n");
                } else {
                    sb.append(more.peek() + "\n");
                }
            } else {
                if (less.size() > more.size()) {
                    sb.append(less.peek() + "\n");
                } else {
                    sb.append(more.peek() + "\n");
                }
            }
        }

        System.out.print(sb);
    }

}
