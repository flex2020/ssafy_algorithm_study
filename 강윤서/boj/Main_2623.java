import java.io.*;
import java.util.*;

public class Main {
    static int N, M, visitCnt;
    static int[] inorder;
    static List<List<Integer>> board;
    static List<Integer> answer;
    static Queue<Integer> Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inorder = new int[N + 1];
        board = new ArrayList<>(N + 1);
        Q = new ArrayDeque<>();

        for (int i = 0; i < N + 1; i++) board.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int prev = -1;
            for (int j = 0; j < value; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    prev = number;
                } else {
                    inorder[number] += 1;
                    board.get(prev).add(number);
                    prev = number;
                }
            }
        }
        answer = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inorder[i] == 0) {
                Q.offer(i);
            }
        }

        while (!Q.isEmpty()) {
            int cur = Q.poll();
            answer.add(cur);
            visitCnt++;
            for (int next : board.get(cur)) {
                inorder[next] -= 1;
                if (inorder[next] == 0) {
                    Q.offer(next);
                }
            }
        }
        if (visitCnt == N) {
            for (int i = 0; i < N; i++) {
                System.out.println(answer.get(i));
            }
        } else {
            System.out.println(0);
        }
    }

}
