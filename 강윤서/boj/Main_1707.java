import java.io.*;
import java.util.*;
public class Main {
    static int V, E;
    static List<List<Integer>> board;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            board = new ArrayList<>();
            number = new int[V+1];

            for (int i=0; i<=V; i++) board.add(new ArrayList<>());
            boolean answer = true;
            for (int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board.get(a).add(b);
                board.get(b).add(a);
            }
            for (int i=1; i<=V; i++) {
                if (number[i] == 0)
                    if (!bfs(i)) answer = false;
            }
            if (answer) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean bfs(int start) {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(start);
        number[start] = 1;
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            for (int next : board.get(cur)) {
                int num = number[cur] == 1 ? 2 : 1;
                if (number[next] == 0) {
                    Q.offer(next);
                    number[next] = num;
                } else if (num != number[next]) {
                    return false;
                }
            }
        }
        return true;
    }
}
