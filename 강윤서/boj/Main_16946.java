package 강윤서.boj;
import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main_16946 {
    static int N, M;
    static int[][] board, cnt, answer;
    static ArrayList<Point> zero;
    static Queue<Point> Q;
    static Point[][] parents;
    static HashMap<Point, Integer> hashMap;

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        cnt = new int[N][M];
        answer = new int[N][M];
        zero = new ArrayList<>();
        parents = new Point[N][M];
        Q = new ArrayDeque<>();
        hashMap = new HashMap<>();
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                board[i][j] = input[j] - '0';
                if (board[i][j] == 0) {
                    zero.add(new Point(i, j));
                    Q.offer(new Point(i, j));
                }
                parents[i][j] = new Point(i, j);
            }
        }
        bfs();

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 0) {
                    Point p = find(parents[i][j]);
                    if (hashMap.containsKey(p)) {
                        int cnt = hashMap.get(p);
                        hashMap.put(p, cnt + 1);
                    } else {
                        hashMap.put(p, 1);
                    }
                }
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (hashMap.containsKey(find(parents[i][j]))) {
                    cnt[i][j] = hashMap.get(find(parents[i][j]));
                }
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 1) {
                    Set<Point> visited = new HashSet<>();
                    if (i >= 1) { // 상
                        if (cnt[i-1][j] > 0 && !visited.contains(find(parents[i-1][j]))) {
                            answer[i][j] += cnt[i - 1][j];
                            visited.add(find(parents[i - 1][j]));
                        }
                    }
                    if (j < M-1) { // 우
                        if (cnt[i][j+1] > 0 && !visited.contains(find(parents[i][j+1]))) {
                            answer[i][j] += cnt[i][j + 1];
                            visited.add(find(parents[i][j + 1]));
                        }
                    }
                    if (i < N-1) { // 하
                        if (cnt[i+1][j] > 0 && !visited.contains(find(parents[i+1][j]))) {
                            answer[i][j] += cnt[i + 1][j];
                            visited.add(find(parents[i + 1][j]));
                        }
                    }
                    if (j >= 1) { // 좌
                        if (cnt[i][j-1] > 0 && !visited.contains(find(parents[i][j-1]))) {
                            answer[i][j] += cnt[i][j-1];
                            visited.add(find(parents[i][j-1]));
                        }
                    }
                    answer[i][j] = (answer[i][j]  + 1) % 10;
                }
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                sb.append(answer[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void bfs() { // 0끼리 union
        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            for (int i=0; i<4; i++) {
                int nr = cur.x + dr[i];
                int nc = cur.y + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] == 0) {
                    union(cur, new Point(nr, nc));
                }
            }
        }
    }
    public static void union(Point a, Point b) {
        Point rootA = find(a);
        Point rootB = find(b);
        parents[rootB.x][rootB.y] = new Point(rootA.x, rootA.y);
    }
    public static Point find(Point a) {
        if (parents[a.x][a.y].equals(a)) {
            return parents[a.x][a.y];
        }
        return parents[a.x][a.y] = find(parents[a.x][a.y]);
    }
}
