package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_2580 {
    static int N = 9;
    static boolean flag = false;
    static int[][] board;
    static int[] cnt;
    static List<int[]> zeroList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new int[N][N];
        cnt = new int[N+1];
        zeroList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    zeroList.add(new int[] {i, j});
                }
            }
        }
        dfs(0);
    }
    public static void dfs(int depth) {
        if (depth == zeroList.size() && !flag) {
            // 보드판 완성 및 첫 출력
            flag = true;
            print();
            return ;
        }
        int r = zeroList.get(depth)[0];
        int c = zeroList.get(depth)[1];
        for (int i=1; i<=N; i++) {
            if (check(r, c, i) && !flag) {
                board[r][c] = i;
                dfs(depth+1);
                board[r][c] = 0;
            }
        }
    }
    public static boolean check(int r, int c, int number) { // (r,c) 좌표에 number 넣기 가능?
        Arrays.fill(cnt, 0);
        // 행 검사
        for (int i=0; i<N; i++) {
            if (board[r][i] == number) return false;
            if (board[r][i] != 0) cnt[board[r][i]]++;
        }
        // 열 검사
        for (int i=0; i<N; i++) {
            if (board[i][c] == number) return false;
            if (board[i][c] != 0) cnt[board[i][c]]++;
        }
        // 사각형 검사
        int startR = (int)(r/3) * 3;
        int startC = (int)(c/3) * 3;
        for (int i=startR; i<startR+3; i++) {
            for (int j=startC; j<startC+3; j++) {
                if (board[i][j] == number) return false;
                if (board[i][j] != 0) cnt[board[i][j]]++;
            }
        }
        for (int i=1; i<=N; i++) {
            if (cnt[i] > 3) return false;
        }
        return true;
    }
    public static void print() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}