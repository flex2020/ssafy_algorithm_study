import java.io.*;
import java.util.*;
public class Main {
    static int N, M, K;
    static int[][] playerBoard;
    static List<Player> playerList;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<List<PriorityQueue<Integer>>> gunList;
    static class Player {
        int idx, r, c, power, dir, point = 0, gunPower = 0;
        boolean hasGun = false;
        Player() {}
        Player (int idx, int r, int c, int power, int dir) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.power = power;
            this.dir = dir;
        }
        public String toString() {
            return "idx: " + idx + " r: " + r + " c: " + c + " power: " + power + " dir: " + dir
                    + " hasGun: " + hasGun + " gunPower: " + gunPower + " point: " + point + "\n";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gunList = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            gunList.add(new ArrayList<>());
            for (int j=0; j<=N; j++) {
                gunList.get(i).add(new PriorityQueue<>((p1, p2) -> p2 - p1));
            }
        }
        playerBoard = new int[N+1][N+1];
        playerList = new ArrayList<>();
        playerList.add(new Player());
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<=N; j++) {
                gunList.get(i).get(j).offer(0);
                if (j == 0) continue;
                gunList.get(i).get(j).offer(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            playerBoard[x][y] = i;
            playerList.add(new Player(i, x, y, s, d));
        }
//        print();
        for (int i=1; i<=K; i++) {
            simulate();
//            System.out.println("=================" + i);
//            print();
        }
        for (int i=1; i<=M; i++) {
            System.out.print(playerList.get(i).point + " ");
        }
    }
    public static void simulate() {
        for (int i=1; i<=M; i++) {
            move(i);
//            System.out.println("이동 완료: " + i);
//            print();
        }
    }
    public static void print() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.println(gunList.get(i).get(j).peek() + " " );
            }
            System.out.println();
        }
        System.out.println("=======");
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(playerBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=======");
        for (int i=1; i<=M; i++) {
            System.out.println(playerList.get(i));
        }
    }
    public static void move(int idx) {
        Player p = playerList.get(idx);
        // 1. 한 칸 이동
        int nr = p.r + dr[p.dir];
        int nc = p.c + dc[p.dir];
        if (nr<1 || nc<1 || nr>N || nc>N) { // 격자 넘어가면 방향 바꿈
            p.dir = (p.dir + 2) % 4;
            nr = p.r + dr[p.dir];
            nc = p.c + dc[p.dir];
        }
        // 2. 이동 칸에 플레이어가 있는지 확인
        if (playerBoard[nr][nc] != 0) {
            Player op = playerList.get(playerBoard[nr][nc]);
//            System.out.println("대결: " + p.idx + " " + op.idx);
            boolean result = false; // 플레이어 승리 여부
            if (p.power + p.gunPower < op.power + op.gunPower
                    || (p.power + p.gunPower == op.power + op.gunPower) && p.power < op.power) { // 상대가 이김
                op.point += (op.power + op.gunPower) - (p.power + p.gunPower);
            } else { // 플레이어가 이김
                p.point += (p.power + p.gunPower) - (op.power + op.gunPower);
                result = true;
            }
//            System.out.println("결과: " + result);
            if (result) { // p가 이김 -> op 가 이동
                // 진 op 총 버림
                if (op.hasGun)
                    gunList.get(nr).get(nc).offer(op.gunPower);
                op.hasGun = false;
                op.gunPower = 0;
                playerBoard[op.r][op.c] = 0; // 이미 있던 애를 이김
                lose(op, p.idx);
                if (gunList.get(nr).get(nc).size() > 1 && gunList.get(nr).get(nc).peek() != 0) {
                    int top = gunList.get(nr).get(nc).poll();
                    if (p.hasGun)
                        gunList.get(nr).get(nc).offer(Math.min(p.gunPower, top));
                    p.hasGun = true;
                    p.gunPower = Math.max(p.gunPower, top);
                }

                playerBoard[nr][nc] = p.idx;
                playerBoard[p.r][p.c] = (playerBoard[p.r][p.c] == p.idx ? 0 : playerBoard[p.r][p.c]);
                p.r = nr;
                p.c = nc;
            } else { // 짐 : p 가 이동
                playerBoard[p.r][p.c] = 0;
                if (p.hasGun)
                    gunList.get(nr).get(nc).offer(p.gunPower);
                p.gunPower = 0;
                p.hasGun = false;
                p.r = nr;
                p.c = nc;
                lose(p, op.idx);
                if (gunList.get(nr).get(nc).size() > 1 && gunList.get(nr).get(nc).peek() != 0) {
                    int top = gunList.get(nr).get(nc).poll();
                    if (op.hasGun)
                        gunList.get(nr).get(nc).offer(Math.min(op.gunPower, top));
                    op.hasGun = true;
                    op.gunPower = Math.max(op.gunPower, top);
                }
            }
        } else {
            if (gunList.get(nr).get(nc).size() > 1) {
//                System.out.println("gun: " + gunList.get(nr).get(nc).peek());
                if (p.hasGun) {
                    int top = gunList.get(nr).get(nc).poll();
                    gunList.get(nr).get(nc).offer(Math.min(p.gunPower, top));
                    p.gunPower = Math.max(p.gunPower, top);
                } else {
                    p.hasGun = true;
                    int top = gunList.get(nr).get(nc).poll();
                    p.gunPower = top;
                }
            }
            playerBoard[nr][nc] = p.idx;
            playerBoard[p.r][p.c] = 0;
            p.r = nr;
            p.c = nc;
        }
    }
    public static void lose(Player p, int passIdx) {
//        System.out.println("lose: " + p + " " + passIdx);
        for (int i=0; i<4; i++) {
            int nr = p.r + dr[(p.dir+i)%4];
            int nc = p.c + dc[(p.dir+i)%4];
            if (1<=nr && nr<=N && 1<=nc && nc<=N && (playerBoard[nr][nc] == 0 || playerBoard[nr][nc] == passIdx)) {
//                System.out.println("hi: " + nr + " " + nc);
                if (gunList.get(nr).get(nc).size() > 1 && gunList.get(nr).get(nc).peek() != 0) {
                    int top = gunList.get(nr).get(nc).poll();
                    p.hasGun = true;
                    p.gunPower = top;
                }
                p.r = nr;
                p.c = nc;
                p.dir = (p.dir+i)%4;
                playerBoard[p.r][p.c] = p.idx;
                return ;
            }
        }
    }
}
