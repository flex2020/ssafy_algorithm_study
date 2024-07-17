import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main {
    static int N, M, P, C, D, activeSanta, round;
    static int[][] board;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1 ,-1};
    static Point rudolph;
    static List<Santa> santaList;
    static class Santa {
        int number, type, score; // [type] -1: 탈락, 2~: 기절풀리는 라운드
        Point point;
        Santa (int number, Point point, int type, int score) {
            this.number = number;
            this.point = point;
            this.type = type;
            this.score = score;
        }
        public String toString() {
            return this.number + " | " + this.point + " | " + this.type + " | " + this.score + "\n";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        board = new int[N+1][N+1];
        activeSanta = P; // 살아있는 산타 수 - 탈락하게 되면 감소

        // 루돌프
        st = new StringTokenizer(br.readLine());
        rudolph = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 산타
        santaList = new ArrayList<>();
        santaList.add(new Santa(0, new Point(0, 0), -1, 0)); // 쓰레기값
        for (int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Point point = new Point(r, c);
            santaList.add(new Santa(num, point, 1, 0));
            board[r][c] = num;
        }
        Collections.sort(santaList, (s1, s2) -> s1.number - s2.number);

        for (int m=1; m<=M; m++) {
            round = m;
            moveRudolph();
            moveSanta();
            if (activeSanta == 0) break;
            givePoint();
            // print();
        }

        for (Santa s : santaList) {
            if (s.number == 0) continue;
            System.out.print(s.score + " ");
        }
    }
    // 루돌프가 이동하는 함수
    public static void moveRudolph() {
        int n = findSanta(); // 목표 산타 번호
        int direction = -1;
        if (rudolph.x < santaList.get(n).point.x) { // 루돌프보다 산타가 아래에
            if (rudolph.y < santaList.get(n).point.y) { // 하우
                direction = 3;
            } else if (rudolph.y > santaList.get(n).point.y) { // 하좌
                direction = 5;
            } else { // 하
                direction = 4;
            }
        } else if (rudolph.x > santaList.get(n).point.x) { // 루돌프보다 산타가 위에
            if (rudolph.y < santaList.get(n).point.y) { // 상우
                direction = 1;
            } else if (rudolph.y > santaList.get(n).point.y) { // 상좌
                direction = 7;
            } else { // 상
                direction = 0;
            }
        } else {
            if (rudolph.y < santaList.get(n).point.y) { // 우
                direction = 2;
            } else if (rudolph.y > santaList.get(n).point.y) { // 좌
                direction = 6;
            }
        }
        int nr = rudolph.x + dr[direction];
        int nc = rudolph.y + dc[direction];
        rudolph = new Point(nr, nc);
        if (board[rudolph.x][rudolph.y] != 0) { // 루돌프가 이동할 때 충돌 발생
            collision(1, board[rudolph.x][rudolph.y], direction);
        }
    }
    // 루돌프가 가장 가까운 산타를 찾는 함수
    public static int findSanta() {
        int number = -1;
        double d = Double.MAX_VALUE;
        Point p = new Point(0, 0);
        for (Santa santa : santaList) {
            if (santa.type == -1) continue; // 탈락
            double dist = Math.pow(Math.abs(rudolph.x - santa.point.x), 2) + Math.pow(Math.abs(rudolph.y - santa.point.y), 2);
            if (dist < d) {
                d = dist;
                p = santa.point;
                number = santa.number;
            } else if (dist == d) {
                if (p.x < santa.point.x) {
                    d = dist;
                    p = santa.point;
                    number = santa.number;
                } else if (p.x == santa.point.x) {
                    if (p.y < santa.point.y) {
                        d = dist;
                        p = santa.point;
                        number = santa.number;
                    }
                }
            }
        }
        return number;
    }
    // 산타가 이동하는 함수
    public static void moveSanta() {
        for (Santa santa : santaList) {
            if (santa.type == -1) continue; // 탈락 산타
            if (santa.type != 1 && santa.type > round) { // 기절 산타
                continue;
            }
            if (santa.type > 1 && santa.type == round) {
                santa.type = 1;
            }
            if (activeSanta == 0) return;
            double initialDistance = Math.pow(Math.abs(rudolph.x - santa.point.x), 2) + Math.pow(Math.abs(rudolph.y - santa.point.y), 2);
            int direction = -1;
            for (int nd=0; nd<8; nd+=2) {
                int nr = santa.point.x + dr[nd];
                int nc = santa.point.y + dc[nd];
                if (nr < 1 || nc < 1 || nr > N || nc > N) continue; // 게임판 밖으로 이동
                if (board[nr][nc] != 0) continue; // 다른 산타가 있음
                double distance = Math.pow(Math.abs(rudolph.x - nr), 2) + Math.pow(Math.abs(rudolph.y - nc), 2);
                if (initialDistance <= distance) continue; // 루돌프와 가까워지지 않음
                direction = nd; // 루돌프와 가장 가까워지는 방향 업데이트
                initialDistance = distance; // 루돌프-산타 거리 업데이트
            }
            if (direction == -1) continue; // 루돌프와 가까워지게 이동할 수 없음
            board[santa.point.x][santa.point.y] = 0;
            santa.point = new Point(santa.point.x + dr[direction], santa.point.y + dc[direction]);
            board[santa.point.x][santa.point.y] = santa.number;

            if (rudolph.x == santa.point.x && rudolph.y == santa.point.y) { // 산타가 이동해서 루돌프와 충돌
                collision(2, santa.number, direction);
            }
        }
    }
    // 루돌프와 산타 충돌
    public static void collision(int type, int num, int dir) {
        // [type] 1: 루돌프 이동시 충돌, 2: 산타 이동시 충돌
        // [num] 충돌이 발생한 산타 번호
        // [dir] 산타든 루돌프든 이동한 방향
        if (dir == -1) return ;
        int nr = santaList.get(num).point.x;
        int nc = santaList.get(num).point.y;
        int nd = dir;
        if (type == 1) {
            santaList.get(num).score += C;
            nr += dr[dir] * C;
            nc += dc[dir] * C;
        } else {
            santaList.get(num).score += D;
            nr += dr[(dir+4)%8] * D;
            nc += dc[(dir+4)%8] * D;
            nd = (dir+4)%8;
        }
        if (nr < 1 || nc < 1 || nr > N || nc > N) { // 밀려나서 게임판 밖 -> 탈락
            santaList.get(num).type = -1;
            board[santaList.get(num).point.x][santaList.get(num).point.y] = 0;
            activeSanta -= 1;
            return;
        }
        if (board[nr][nc] != 0) { // 다른 산타가 있음 -> 상호작용
            interaction(board[nr][nc], nd);
        }
        board[santaList.get(num).point.x][santaList.get(num).point.y] = 0;
        board[nr][nc] = num;
        santaList.get(num).point = new Point(nr, nc);
        // 루돌프와 충돌 이후 산타는 기절
        santaList.get(num).type = round+2; // 기절이 해제되는 라운드 저장 
    }
    // 산타 충돌 후 산타(num)와 만남 -> 상호작용
    // num이 해당 방향으로 +1 
    public static void interaction(int num, int dir) {
        int nr = santaList.get(num).point.x + dr[dir];
        int nc = santaList.get(num).point.y + dc[dir];
        if (nr < 1 || nc < 1 || nr > N || nc > N) { // 밀려나서 게임판 밖 -> 탈락
            santaList.get(num).type = -1;
            activeSanta -= 1;
            return ;
        }
        if (board[nr][nc] != 0) { // 만약 그 위치에 또 산타가 있으면 연쇄 이동
            interaction(board[nr][nc], dir);
        }
        board[nr][nc] = num;
        santaList.get(num).point = new Point(nr, nc);
    }
    public static void givePoint() {
        for (Santa s : santaList) {
            if (s.type == -1) continue; // 탈락 상태 아닌 모든 산타에게 +1
            s.score += 1;
        }
    }
    public static void print() {
        System.out.println("============");
        System.out.println(rudolph);
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(board[i][j] + " " );
            }
            System.out.println();
        }
        for (Santa s : santaList) {
            if (s.number == 0) continue;
            System.out.print(s.score + " ");
        }
        for (Santa s : santaList) {
            if (s.number == 0) continue;
            System.out.print(s);
        }
        System.out.println("==========");
    }
}
