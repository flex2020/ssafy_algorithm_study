import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N, M, K;
    static int[] dr = {0, 1, 0, -1, -1, -1, 1, 1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0, -1, 1, -1, 1};
    static Turret[][] board;
    static boolean[][] isRelated;

    static class Turret {
        int r, c, idx, power;

        Turret(int r, int c, int idx, int power) {
            this.r = r;
            this.c = c;
            this.idx = idx;
            this.power = power;
        }

        @Override
        public String toString() {
            return "Turret{" +
                    "r=" + r +
                    ", c=" + c +
                    ", idx=" + idx +
                    ", power=" + power +
                    '}';
        }
    }

    static class Node {
        int r, c, dist, dir;
        List<Point> path;

        Node(int r, int c, int dist, int dir, List<Point> path) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.dir = dir;
            this.path = path;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    ", dir=" + dir +
                    ", path=" + path +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new Turret[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                board[i][j] = new Turret(i, j, 0, value);
            }
        }

        for (int i = 1; i <= K; i++) {
//            System.out.println(i + "번째 라운드~~");
            if (remainOneTurret()) {
                break;
            }
            Turret attacker = findAttacker();
            Turret target = findTarget();
            isRelated = new boolean[N][M];
            isRelated[attacker.r][attacker.c] = true;
            isRelated[target.r][target.c] = true;
//            System.out.println("attacker: " + attacker);
//            System.out.println("target: " + target);
            attacker.power += N + M;
            if (!attackByLaser(attacker, target)) {
//                System.out.println("포탑공격 ㄱㄱ");
                attakByCannonBall(attacker, target);
            }
            addPower();
            attacker.idx = i;
//            print();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j].power > answer) answer = board[i][j].power;
            }
        }
        System.out.println(answer);
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    public static boolean remainOneTurret() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j].power > 0) cnt++;
            }
        }
        if (cnt <= 1) return true;
        else return false;
    }

    public static Turret findAttacker() {
        Turret attacker = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j].power <= 0) continue; // 이미 죽은거는 스킵
                if (attacker == null) { // 공격자가 null이라면 세팅
                    attacker = board[i][j];
                    continue;
                }
                if (board[i][j].power < attacker.power) { // 1. 공격력이 더 낮은 경우
                    attacker = board[i][j];
                } else if (board[i][j].power == attacker.power) {
                    if (attacker.idx < board[i][j].idx) { // 2. 가장 최근에 공격한 경우
                        attacker = board[i][j];
                    } else if (attacker.idx == board[i][j].idx) {
                        if (attacker.r + attacker.c < board[i][j].r + board[i][j].c) { // 3. 행과 열의 합이 더 큰 경우
                            attacker = board[i][j];
                        } else if (attacker.r + attacker.c == board[i][j].r + board[i][j].c) {
                            if (attacker.c < board[i][j].c) { // 4. 열이 더 큰 경우
                                attacker = board[i][j];
                            }
                        }
                    }
                }
            }
        }
        return attacker;
    }

    public static Turret findTarget() {
        Turret target = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j].power <= 0) continue; // 이미 죽은거는 스킵
                if (target == null) { // 타깃이 null이라면 세팅
                    target = board[i][j];
                    continue;
                }
                if (board[i][j].power > target.power) { // 1. 공격력이 더 큰 경우
                    target = board[i][j];
                } else if (board[i][j].power == target.power) {
                    if (target.idx > board[i][j].idx) { // 2. 공격한지 더 오래된 경우
                        target = board[i][j];
                    } else if (target.idx == board[i][j].idx) {
                        if (target.r + target.c > board[i][j].r + board[i][j].c) { // 3. 행과 열의 합이 더 작은 경우
                            target = board[i][j];
                        } else if (target.r + target.c == board[i][j].r + board[i][j].c) {
                            if (target.c > board[i][j].c) { // 4. 열이 더 작은 경우
                                target = board[i][j];
                            }
                        }
                    }
                }
            }
        }
        return target;
    }

    // 레이저 공격 가능 여부
    public static boolean attackByLaser(Turret attacker, Turret target) {
        Queue<Node> Q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        Q.offer(new Node(attacker.r, attacker.c, 0, -1, new ArrayList<>())); // r, c, dist, dir
        visited[attacker.r][attacker.c] = true;

        while (!Q.isEmpty()) {
            Node cur = Q.poll();
//            System.out.println(cur);
            if (cur.r == target.r && cur.c == target.c) {
//                System.out.println("경로: " + cur.path);
                for (Point p : cur.path) {
                    isRelated[p.x][p.y] = true;
                    if (p.x == target.r && p.y == target.c) {
                        board[p.x][p.y].power -= attacker.power;
                        continue;
                    }
                    board[p.x][p.y].power -= attacker.power / 2;
                }
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                // 범위 넘었을 때
                if (nr < 0) nr = N - 1;
                else if (nc < 0) nc = M - 1;
                else if (nr >= N) nr = 0;
                else if (nc >= M) nc = 0;

                if (board[nr][nc].power <= 0) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                List<Point> path = new ArrayList<>(cur.path);
                path.add(new Point(nr, nc));
                Q.offer(new Node(nr, nc, cur.dist + 1, i, path));
            }
        }
        return false;
    }

    // 포탄 공격
    public static void attakByCannonBall(Turret attacker, Turret target) {
        board[target.r][target.c].power -= attacker.power;
        for (int i = 0; i < 8; i++) {
            int nr = target.r + dr[i];
            int nc = target.c + dc[i];

            if (nr < 0) nr = N - 1;
            if (nc < 0) nc = M - 1;
            if (nr >= N) nr = 0;
            if (nc >= M) nc = 0;

            if (board[nr][nc].power <= 0) continue;
            if (nr == attacker.r && nc == attacker.c) continue;
            isRelated[nr][nc] = true;
            board[nr][nc].power -= (attacker.power / 2);
        }
    }

    // 포탑 정비
    public static void addPower() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isRelated[i][j]) continue;
                if (board[i][j].power <= 0) continue;
                board[i][j].power += 1;
            }
        }
    }
}
