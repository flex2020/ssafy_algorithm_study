package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_17143 {
    static int R, C, M, answer;
    static Shark[][] board;
    static boolean[][] visited; // 동시 이동인지 판별하기 위해
    static Shark[] sharkArray;
    static int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, 1, -1};
    static class Shark {
        int idx, r, c, speed, dir, size;
        Shark (int idx, int r, int c, int speed, int dir, int size) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
        @Override
        public String toString() {
            return "r: " + r + " c: " + c + " speed: " + speed + " dir: " + dir + " size: " + size;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R+1][C+1];
        visited = new boolean[R+1][C+1];
        for (int i=0; i<=R; i++) {
            for (int j=0; j<=C; j++) {
                board[i][j] = null;
            }
        }
        sharkArray = new Shark[M];
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark newShark = new Shark(i, r, c, s, d, z);
            board[r][c] = newShark;
            sharkArray[i] = newShark;
        }
        if (M > 0) {
            for (int j=1; j<=C; j++) { // 1. 낚시왕 움직이기
                for (int i=1; i<=R; i++) {
                    if (board[i][j] != null) { // 2. 지면에서 가장 가까운 상어 잡기
                        answer += board[i][j].size;
                        // 잡힌 상어 없애주기
                        sharkArray[board[i][j].idx] = null;
                        board[i][j] = null;
                        break;
                    }
                }
                // 3. 상어 움직이기
                for (int i=0; i<=R; i++) { // 이동 전에 방문 배열 초기화 -> 동시 이동임을 보장
                    Arrays.fill(visited[i], false);
                }
                moveShark();
            }
        }
        System.out.println(answer);
    }
    public static void moveShark() {
        for (int i=0; i<M; i++) {
            if (sharkArray[i] != null) {
                Shark cur = sharkArray[i];
                int nr = cur.r;
                int nc = cur.c;
                int dist = 0;
                if (cur.dir <= 2) { // 상하
                    while (dist < cur.speed) {
                        if (nr <= 1) { // 위로 넘어감 (현재 방향: 위)
                            cur.dir = 2;
                            nr = 1;
                        } else if (nr >= R) { // 아래로 넘어감 (현재 방향: 아래)
                            cur.dir = 1;
                            nr = R;
                        }
                        nr += dr[cur.dir];
                        dist++;
                    }
                } else { // 좌우
                    while (dist < cur.speed) {
                        if (nc <= 1) { // 왼쪽으로 넘어감 (현재 방향: 왼쪽)
                            cur.dir = 3;
                            nc = 1;
                        } else if (nc >= C) { // 오른쪽으로 넘어감 (현재 방향: 오른쪽)
                            cur.dir = 4;
                            nc = C;
                        }
                        nc += dc[cur.dir];
                        dist++;
                    }
                }
                if (!visited[cur.r][cur.c]) { // 이미 다른 애가 동시이동으로 이동 전에 걸 들어오지 않았을 때
                    board[cur.r][cur.c] = null;
                }
                cur.r = nr;
                cur.c = nc;
                if (visited[cur.r][cur.c]) {
                    if (board[cur.r][cur.c].size > cur.size) { // cur보다 큰 게 이미 위치
                        sharkArray[cur.idx] = null;
                    } else { // cur이 먹어야 함
                        sharkArray[board[cur.r][cur.c].idx] = null;
                        sharkArray[cur.idx] = cur;
                        board[cur.r][cur.c] = cur;
                    }
                } else {
                    sharkArray[cur.idx] = cur;
                    board[cur.r][cur.c] = cur;
                    visited[cur.r][cur.c] = true;
                }
            }
        }
    }
    public static void print() {
        for (int i=1; i<=R; i++) {
            for (int j=1; j<=C; j++) {
                if (board[i][j] == null) System.out.print("null ");
                else System.out.print(board[i][j].idx + " " + board[i][j].dir + " " + board[i][j].size + " ");
            }
            System.out.println();
        }
    }
}
