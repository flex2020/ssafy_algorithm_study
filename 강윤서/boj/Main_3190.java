package 강윤서.boj;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main_3190 {
    static int N, K, L, time, dir = 0;
    static int[][] board;
    static Deque<Point> snake;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0}; // 우 하 좌 상
    static List<int[]> timeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        snake = new ArrayDeque<>();
        snake.offer(new Point(0, 0));
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            board[r][c] = 1;
        }
        L = Integer.parseInt(br.readLine());
        timeList = new ArrayList<>();
        for (int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            if (d.equals("L")) { // 왼쪽
                timeList.add(new int[] {t, -1});
            } else if (d.equals("D")) { // 오른쪽
                timeList.add(new int[] {t, 1});
            }
        }
        while (true) {
            time++;
            Point curHead = snake.peekFirst();
            Point nextHead = new Point(curHead.x + dr[dir], curHead.y + dc[dir]);
            if (nextHead.x < 0 || nextHead.x >= N || nextHead.y < 0 || nextHead.y >= N) break;
            if (snake.contains(nextHead)) break;
            snake.offerFirst(nextHead);
            if (board[nextHead.x][nextHead.y] == 1) { // 사과 있음
                board[nextHead.x][nextHead.y] = 0; // 사과 없애주기
            } else {
                snake.pollLast();
            }

            if (!timeList.isEmpty() && time == timeList.get(0)[0]) {
                dir += timeList.get(0)[1];
                if (dir < 0) dir = 3;
                else if (dir >= 4) dir = 0;
                timeList.remove(0);
            }
        }
        System.out.println(time);

    }

}