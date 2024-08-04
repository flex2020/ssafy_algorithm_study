import java.util.*;
import java.io.*;
public class Main {
    static int N, M, K, sumDist;
    static int[][] board, personBoard;
    // 배열 돌릴 때 사용
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    // 사람 이동할 때 사용
    static int[] pdr = {-1, 1, 0, 0};
    static int[] pdc = {0, 0, -1, 1};
    static List<Person> list;
    static class Person {
        int idx, r, c;
        boolean isExited;
        Person (int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.isExited = false;
        }
        public String toString() {
            return this.idx + " " + this.r + " " + this.c + " " + this.isExited + "\n";
        }
    }
    static int[] exitPoint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        personBoard = new int[N+1][N+1];
        list = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Person(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            personBoard[list.get(i).r][list.get(i).c] |= 1 << i;
        }
        st = new StringTokenizer(br.readLine());
        exitPoint = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        board[exitPoint[0]][exitPoint[1]] = -1; // 출입구 표시

        for (int i=0; i<K; i++) {
            movePeople();

            if (M <= 0) {
                // System.out.println(i);
                break;
            }
            findSquare();
            // System.out.println(i + " ================= " + exitPoint[0] + " " + exitPoint[1] + " " + sumDist);
            // print();
            // System.out.println(list);
        }
        System.out.println(sumDist);
        System.out.println(exitPoint[0] + " " + exitPoint[1]);
    }
    public static void print() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("========================");
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                System.out.print(personBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    // 참가자 이동
    public static void movePeople() {
        for (Person person : list) {
            if (person.isExited) continue;
            int curDist = Math.abs(person.r - exitPoint[0]) + Math.abs(person.c - exitPoint[1]);
            for (int d=0; d<4; d++) {
                int nr = person.r + pdr[d];
                int nc = person.c + pdc[d];
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (board[nr][nc] > 0) continue; 
                int newDist = Math.abs(nr - exitPoint[0]) + Math.abs(nc - exitPoint[1]);
                if (newDist < curDist) { // 출입구에 가까워짐
                    personBoard[person.r][person.c] &= ~(1 << person.idx);
                    person.r = nr;
                    person.c = nc;
                    personBoard[person.r][person.c] |= 1 << person.idx;
                    sumDist += 1;
                    if (newDist == 0) { // 출입구 도착! 
                        person.isExited = true;
                        personBoard[person.r][person.c] &= ~(1 << person.idx);
                        M--;
                    }
                    break;
                }
            }
        }
    }

    // 회전할 정사각형 범위 찾기
    public static void findSquare() {
        for (int width=2; width<=N; width++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (i + width - 1 > N || j + width - 1 > N) continue; 
                    if (exitPoint[0]<i || exitPoint[0]>=i+width || exitPoint[1]<j || exitPoint[1]>=j+width) continue;
                    boolean flag = false; // 해당 범위 내에 사람이 있는지 확인
                    for (Person p : list) {
                        if (p.isExited) continue;
                        if (p.r >= i && p.r < i + width && p.c >= j && p.c < j + width) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        rotateBoard(i, j, width);
                        return ;
                    }
                }
            }
        }
    }

    // 미로 회전 (시계방향)
    public static void rotateBoard(int startR, int startC, int width) {
        // System.out.println("@#@#@#@#@#@#@@##@#@@@#@#" + startR + " " + startC + " " + width);
        // print();
        for (int i = 0; i <= (width-1)/2; i++) { // 테두리 사각형
            int sr = startR + i;
            int sc = startC + i; // 테두리에서 가장 왼쪽 윗점 잡기
            int[] npr = {startR+width-i-1, startR+width-i-1, sr, sr};
            int[] npc = {sc, startC+width-i-1, startC+width-i-1, sc};
            if (width - 2*i == 1 && board[sr][sc] >= 1) board[sr][sc]--;
            // System.out.println(Arrays.toString(npr));
            // System.out.println(Arrays.toString(npc));
            for (int x=0; x<width-2*i-1; x++) { // 시작점 변경
                int cr = sr;
                int cc = sc + x;
                if (cr<startR || sc<startC || cr>=startR+width || cc >= startC + width) break;
                int first = board[cr][cc];
                int firstPerson = personBoard[cr][cc];
                for (int d=0; d<4; d++) {
                    int nr = npr[d] + dr[d] * x;
                    int nc = npc[d] + dc[d] * x;
                    if (d == 3) { // 처음 거 건드리기
                        board[cr][cc] = first;
                        if (board[cr][cc] == -1) exitPoint = new int[] {cr, cc};
                        else if (board[cr][cc] >= 1) board[cr][cc]--;

                        if (firstPerson >= 1) {
                            for (Person p : list) {
                                if (p.isExited) continue;
                                if ((firstPerson & (1 << p.idx)) != 0) {
                                    p.r = cr;
                                    p.c = cc;
                                    personBoard[sr][sc+x] &= ~(1 << p.idx);
                                    personBoard[cr][cc] |= 1 << p.idx;
                                }
                            }
                        }
                    } else {
                        board[cr][cc] = board[nr][nc];
                        if (board[cr][cc] == -1) exitPoint = new int[] {cr, cc};
                        else if (board[cr][cc] >= 1) board[cr][cc]--;

                        if (personBoard[nr][nc] >= 1) { // a->b일 때, a점에 사람이 있었다면

                            for (Person p : list) {
                                if (p.isExited) continue;
                                if ((personBoard[nr][nc] & (1 << p.idx)) != 0) {
                                    p.r = cr;
                                    p.c = cc;
                                    personBoard[nr][nc] &= ~(1 << p.idx);
                                    personBoard[cr][cc] |= 1 << p.idx;
                                }
                            }
                        }
                    }
                    cr = nr;
                    cc = nc;
                }
            }
            
        }
    }
}
