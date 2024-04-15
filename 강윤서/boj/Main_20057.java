package 강윤서.boj;
import java.io.*;
import java.util.*;

public class Main_20057 {
    static int N, r, c, d, move, cnt, dir, answer;
    static int[][] board;
    static Queue<int[]> Q;
    static int[] dr = {0, 1, 0, -1}, dc = {-1, 0, 1, 0};
    static int[] value = {7, 7, 1, 1, 2, 2, 10, 10, 5}; // 0~1: 7 / 2~3: 1 / 4~5: 2 / 6~7: 10 / 8: 5
    static int[][] next = {{-1, 0}, {1, 0}, {1, 1}, {-1, 1}, {-2, 0}, {2, 0}, {-1, -1}, {1, -1}, {0, -2}}; // 왼쪽으로 날리기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        Q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        r = N / 2; // 중심점에서 시작
        c = N / 2;
        d = 1; // 처음 이동해야 하는 거리 : 1
        dir = 0; // 처음 방향
        while (r >= 0 && c >= 0) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            Q.offer(new int[]{nr, nc, dir, r, c});
            move++;
            if (move == d) {
                spread();
                dir = (dir + 1) % 4;
                move = 0;
                cnt++;
            }
            if (cnt == 2) {
                d += 1;
                cnt = 0;
            }
            r = nr;
            c = nc;
        }
        System.out.println(answer);
    }

    public static void spread() {
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            if (cur[0] < 0 || cur[0] >= N || cur[1] < 0 || cur[1] >= N) continue;
            int v = board[cur[0]][cur[1]]; // 날릴 먼지의 양
            int alphaR = cur[0] + dr[cur[2]];
            int alphaC = cur[1] + dc[cur[2]];
            for (int i=0; i<9; i++) {
                int nextR = cur[0];
                int nextC = cur[1];
                if (cur[2] == 0) { // 좌
                    nextR += next[i][0];
                    nextC += next[i][1];
                } else if (cur[2] == 1) { // 하
                    nextR += next[i][1] * (-1);
                    nextC += next[i][0] * (-1);
                } else if (cur[2] == 2) { // 우
                    nextR += next[i][0] * (-1);
                    nextC += next[i][1] * (-1);
                } else { // 상
                    nextR += next[i][1];
                    nextC += next[i][0];
                }
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) { // 먼지가 격자 밖으로
                    answer += board[cur[0]][cur[1]] * value[i] / 100;
                    v -= board[cur[0]][cur[1]] * value[i] / 100;
                } else {
                    board[nextR][nextC] += board[cur[0]][cur[1]] * value[i] / 100;
                    v -= board[cur[0]][cur[1]] * value[i] / 100;
                }
            }
            if (alphaR < 0 || alphaR >= N || alphaC < 0 || alphaC >= N) {
                answer += v;
            } else {
                board[alphaR][alphaC] += v;
            }
            board[cur[0]][cur[1]] = board[cur[3]][cur[4]];
            board[cur[3]][cur[4]] = 0;

//            System.out.println(Arrays.toString(cur));
//            print();
//            System.out.println("!@!@!@!@!@!@");
        }
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
