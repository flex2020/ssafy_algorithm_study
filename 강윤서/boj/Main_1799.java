package 강윤서.boj;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main_1799 {
    static int N, blackMax, whiteMax;
    static int[][] board;
    static Set<Point> bishop;
    static List<Point> black, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        bishop = new HashSet<>();
        black = new ArrayList<>();
        white = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    if ((i + j) % 2 == 0) white.add(new Point(i, j));
                    else black.add(new Point(i, j));
                }
            }
        }
        dfs(0, 0, 0); // 흑
        dfs(0, 1, 0); // 백
        System.out.println(blackMax + whiteMax);
    }

    public static void dfs(int depth, int color, int cnt) {
        if (color == 0 && depth == black.size()) {
            blackMax = Math.max(blackMax, cnt);
            return ;
        }
        if (color == 1 && depth == white.size()) {
            whiteMax = Math.max(whiteMax, cnt);
            return ;
        }
        boolean can = true; // true: 놓을 수 있음 / false: 놓을 수 없음
        for (Point bis : bishop) { // 놓인 비숍 확인
            if (color == 0) { // 흑
                if (Math.abs(bis.x - black.get(depth).x) == Math.abs(bis.y - black.get(depth).y)) {
                    can = false;
                }
            } else { // 백
                if (Math.abs(bis.x - white.get(depth).x) == Math.abs(bis.y - white.get(depth).y)) {
                    can = false;
                }
            }
        }
        if (can) {
            if (color == 0) {
                bishop.add(black.get(depth));
                dfs(depth + 1, color, cnt + 1);
                bishop.remove(black.get(depth));
            } else {
                bishop.add(white.get(depth));
                dfs(depth + 1, color, cnt + 1);
                bishop.remove(white.get(depth));
            }
        }
        dfs(depth+1, color, cnt);
    }
}
