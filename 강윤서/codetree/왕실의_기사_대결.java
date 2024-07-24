import java.io.*;
import java.util.*;
public class Main {
    static int L, N, Q, answer;
    static int[][] board, knightBoard;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1}; // 상우하좌
    static class Knight {
        int r, c, h, w, life, attack, state; // [state] 1: 살아있음, 2: 사라짐
        Knight (int r, int c, int h, int w, int life, int attack, int state) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.life = life;
            this.attack = attack;
            this.state = state;
        }
        public String toString() {
            return "위치: " + r + " " + c + " 크기: " + h + " " + w + " 생명:" + life + " 공격:" + attack + " 상태:" + state + "\n";
        }
    }
    static List<Knight> knightList;
    static List<Integer> canMove;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        board = new int[L+1][L+1];
        knightBoard = new int[L+1][L+1]; // 기사의 번호 저장
        knightList = new ArrayList<>();
        for (int i=1; i<=L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=L; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        knightList.add(new Knight(0, 0, 0, 0, 0, 0, 2));
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int a=0; a<h; a++) {
                for (int b=0; b<w; b++) {
                    knightBoard[r+a][c+b] = i;
                }
            }
            knightList.add(new Knight(r, c, h, w, k, 0, 1));
        }
        for (int q = 0; q<Q; q++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (knightList.get(num).state == 2) continue;
            canMove = new ArrayList<>();
            if (findKnightToMove(num, dir))
                moveKnight(num, canMove, dir);
        }
        for (int i=1; i<=N; i++) {
            Knight cur = knightList.get(i);
            if (cur.state == 1) answer += cur.attack;
        }
        System.out.println(answer);
    }
    // 명령에 움직일 기사 리스트 확인
    public static boolean findKnightToMove(int num, int dir) {
        Knight curKnight = knightList.get(num);
        Set<Integer> next = new HashSet<>(); // num이 움직인다면 다음 연쇄로 움직일 수 있는 기사
        boolean flag = true;
        switch (dir) {
            case 0: // 상 - 윗쪽 확인
                for (int i=0; i<curKnight.w; i++) {
                    int nr = curKnight.r - 1;
                    int nc = curKnight.c + i;
                    if (nr < 1 || nr > L || nc < 1 || nc > L || board[nr][nc] == 2) {
                        flag = false;
                        break;
                    }
                    if (knightBoard[nr][nc] != num && knightList.get(knightBoard[nr][nc]).state != 2) next.add(knightBoard[nr][nc]);
                }
                break;
            case 1: // 우 - 오른쪽 확인
                for (int i=0; i<curKnight.h; i++) {
                    int nr = curKnight.r + i;
                    int nc = curKnight.c + curKnight.w ;
                    if (nr < 1 || nr > L || nc < 1 || nc > L || board[nr][nc] == 2) {
                        flag = false;
                        break;
                    }
                    if (knightBoard[nr][nc] != num && knightList.get(knightBoard[nr][nc]).state != 2) next.add(knightBoard[nr][nc]);
                }
                break;
            case 2: // 하 - 아랫쪽 확인
                for (int i=0; i<curKnight.w; i++) {
                    int nr = curKnight.r + curKnight.h;
                    int nc = curKnight.c + i;
                    if (nr < 1 || nr > L || nc < 1 || nc > L || board[nr][nc] == 2) {
                        flag = false;
                        break;
                    }
                    if (knightBoard[nr][nc] != num && knightList.get(knightBoard[nr][nc]).state != 2) next.add(knightBoard[nr][nc]);
                }
                break;
            case 3: // 좌 - 왼쪽 확인
                for (int i=0; i<curKnight.h; i++) {
                    int nr = curKnight.r + i;
                    int nc = curKnight.c - 1;
                    if (nr < 1 || nr > L || nc < 1 || nc > L || board[nr][nc] == 2) {
                        flag = false;
                        break;
                    }
                    if (knightBoard[nr][nc] != num && knightList.get(knightBoard[nr][nc]).state != 2) next.add(knightBoard[nr][nc]);
                }
                break;
        }
        if (flag) {
            if (canMove.indexOf(num) != -1) return true;
            canMove.add(num); // 움직일 수 있음
            if (next.isEmpty()) {
                return true;
            }
            for (int n : next) {
                if (knightList.get(n).state == 2) continue; 
                if (canMove.indexOf(n) != -1) continue;
                if (findKnightToMove(n, dir)) {
                    if (canMove.indexOf(n) != -1) continue;
                    canMove.add(n);
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // 기사 움직이기
    public static void moveKnight(int start, List<Integer> next, int dir) {
        // System.out.println(start + " " + next + " " + dir);
        // System.out.println(knightList);
        // print();
        // next의 끝에서부터 움직이고 생명 계산
        for (int i=next.size()-1; i>=0; i--) {
            Knight cur = knightList.get(next.get(i));
            
            int loss = 0;
            for (int a=0; a<cur.h; a++) {
                for (int b=0; b<cur.w; b++) {
                    int nr = cur.r + a + dr[dir];
                    int nc = cur.c + b + dc[dir];
                    if (board[nr][nc] == 1) loss += 1;
                    knightBoard[nr][nc] = next.get(i);
                }
            }
            
            switch (dir) {
                case 0: // 상 -> 맨 아랫변 0으로
                    for (int n=0; n<cur.w; n++) {
                        knightBoard[cur.r+cur.h-1][cur.c+n] = 0;
                    }
                    break;
                case 1: // 우
                    for (int n=0; n<cur.h; n++) {
                        knightBoard[cur.r+n][cur.c] = 0;
                    }
                    break;
                case 2: // 하
                    for (int n=0; n<cur.w; n++) {
                        knightBoard[cur.r][cur.c+n] = 0;
                    }
                    break;
                case 3: // 좌
                    for (int n=0; n<cur.h; n++) {
                        knightBoard[cur.r+n][cur.c+cur.w-1] = 0;
                    }
                    break;
            }
            if (start != next.get(i)) {
                if (cur.attack + loss >= cur.life) { // loss >= cur.life 라고 해서 틀림
                    cur.state = 2;
                    for (int a=1; a<=L; a++) {
                        for (int b=1; b<=L; b++) {
                            if (knightBoard[a][b] == next.get(i)) 
                                knightBoard[a][b] = 0;
                        }
                    }
                } else {
                    cur.attack += loss;
                }
            }
            
            knightList.set(next.get(i), new Knight(cur.r + dr[dir], cur.c + dc[dir], cur.h, cur.w, cur.life, cur.attack, cur.state));
        }
    }
    public static void print() {
        for (int i=1; i<=L; i++) {
            for (int j=1; j<=L; j++) {
                System.out.print(knightBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====");
    }
}
