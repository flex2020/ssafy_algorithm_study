package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_2239 {
    static int N = 9;
    static boolean flag = false;
    static int[][] board;
    static List<int[]> zero;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[N][N];
        zero = new ArrayList<>();
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<N; j++) {
                board[i][j] = input[j] - '0';
                if (board[i][j] == 0) zero.add(new int[] {i, j});
            }
        }
        dfs(board, 0);
    }
    public static void dfs(int[][] map, int depth) {
        if (depth == zero.size()) {
            if (!flag) {
                flag = true;
                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        System.out.print(map[i][j]);
                    }
                    System.out.println();
                }
            }
            return ;
        }
        if (flag) return ;
        int[][] board = copyBoard(map);
        int r = zero.get(depth)[0];
        int c = zero.get(depth)[1];
        for (int i=1; i<=9; i++) {
            if (check(board, r, c, i)) {
                board[r][c] = i;
                dfs(board, depth+1);
            }
        }
    }
    public static boolean check (int[][] board, int r, int c, int value) {
        board[r][c] = value;
        int[] count = new int[10];
        for (int i=0; i<N; i++) {
            if (board[i][c] != 0) count[board[i][c]]++;
            if (board[r][i] != 0) count[board[r][i]]++;
        }
        int startR = r/3*3;
        int startC = c/3*3;
        for (int i=startR; i<startR+3; i++) {
            for (int j=startC; j<startC+3; j++) {
                count[board[i][j]]++;
            }
        }
        for (int i=1; i<=N; i++) {
            if (count[i] > 3) { return false;}
        }
        return true;
    }
    public static int[][] copyBoard(int[][] map) {
        int[][] newBoard = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                newBoard[i][j] = map[i][j];
            }
        }
        return newBoard;
    }
}
