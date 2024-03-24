package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_12100 {
    static int N, answer;
    static int[][] board;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, board[i][j]);
            }
        }
        for (int d=0; d<4; d++) {
            dfs(0, 0, d, board);
        }
        System.out.println(answer);
    }
    public static void dfs(int depth, int maxValue, int dir, int[][] map) {
        if (depth == 5) {
            answer = Math.max(answer, maxValue);
            return ;
        }
        int[][] board = copyMap(map);
        int value = 0;
        if (dir == 0) { // 상
            for (int c=0; c<N; c++) {
                Deque<int[]> DQ = new ArrayDeque<>();
                for (int r=0; r<N; r++) {
                    if (board[r][c] == 0) continue;
                    if (DQ.isEmpty()) {
                        DQ.offerLast(new int[] {board[r][c], 0}); // 0: 아직 합해지지 않음
                    } else {
                        if (DQ.peekLast()[0] == board[r][c] && DQ.peekLast()[1] == 0) {
                            DQ.pollLast();
                            DQ.offerLast(new int[] {board[r][c]*2, 1});
                        } else {
                            DQ.offerLast(new int[] {board[r][c], 0});
                        }
                    }
                }
                for (int r=0; r<N; r++) {
                    if (!DQ.isEmpty()) {
                        board[r][c] = DQ.pollFirst()[0];
                        value = Math.max(value, board[r][c]);
                    } else {
                        board[r][c] = 0;
                    }
                }
            }
        } else if (dir == 1) { // 하
            for (int c=0; c<N; c++) {
                Deque<int[]> DQ = new ArrayDeque<>();
                for (int r=N-1; r>=0; r--) {
                    if (board[r][c] == 0) continue;
                    if (DQ.isEmpty()) {
                        DQ.offerLast(new int[] {board[r][c], 0}); // 0: 아직 합해지지 않음
                    } else {
                        if (DQ.peekLast()[0] == board[r][c] && DQ.peekLast()[1] == 0) {
                            DQ.pollLast();
                            DQ.offerLast(new int[] {board[r][c]*2, 1});
                        } else {
                            DQ.offerLast(new int[] {board[r][c], 0});
                        }
                    }
                }
                for (int r=N-1; r>=0; r--) {
                    if (!DQ.isEmpty()) {
                        board[r][c] = DQ.pollFirst()[0];
                        value = Math.max(value, board[r][c]);
                    } else {
                        board[r][c] = 0;
                    }
                }
            }
        } else if (dir == 2) { // 좌
            for (int r=0; r<N; r++) {
                Deque<int[]> DQ = new ArrayDeque<>();
                for (int c=0; c<N; c++) {
                    if (board[r][c] == 0) continue;
                    if (DQ.isEmpty()) {
                        DQ.offerLast(new int[] {board[r][c], 0}); // 0: 아직 합해지지 않음
                    } else {
                        if (DQ.peekLast()[0] == board[r][c] && DQ.peekLast()[1] == 0) {
                            DQ.pollLast();
                            DQ.offerLast(new int[] {board[r][c]*2, 1});
                        } else {
                            DQ.offerLast(new int[] {board[r][c], 0});
                        }
                    }
                }
                for (int c=0; c<N; c++) {
                    if (!DQ.isEmpty()) {
                        board[r][c] = DQ.pollFirst()[0];
                        value = Math.max(value, board[r][c]);
                    } else {
                        board[r][c] = 0;
                    }
                }
            }
        } else { // 우
            for (int r=0; r<N; r++) {
                Deque<int[]> DQ = new ArrayDeque<>();
                for (int c=N-1; c>=0; c--) {
                    if (board[r][c] == 0) continue;
                    if (DQ.isEmpty()) {
                        DQ.offerLast(new int[] {board[r][c], 0}); // 0: 아직 합해지지 않음
                    } else {
                        if (DQ.peekLast()[0] == board[r][c] && DQ.peekLast()[1] == 0) {
                            DQ.pollLast();
                            DQ.offerLast(new int[] {board[r][c]*2, 1});
                        } else {
                            DQ.offerLast(new int[] {board[r][c], 0});
                        }
                    }
                }
                for (int c=N-1; c>=0; c--) {
                    if (!DQ.isEmpty()) {
                        board[r][c] = DQ.pollFirst()[0];
                        value = Math.max(value, board[r][c]);
                    } else {
                        board[r][c] = 0;
                    }
                }
            }
        }
        dfs(depth+1, value, 0,board);
        dfs(depth+1, value, 1,board);
        dfs(depth+1, value, 2,board);
        dfs(depth+1, value, 3,board);
    }
    public static int[][] copyMap(int[][] board) {
        int[][] newBoard = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
}
