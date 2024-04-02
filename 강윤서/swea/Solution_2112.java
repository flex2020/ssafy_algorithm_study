package 강윤서.swea;

import java.io.*;
import java.util.*;
public class Solution_2112 {
    static int D, W, K, answer;
    static int[][] board, init;
    static List<Integer> selected;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            init = new int[D][W];
            selected = new ArrayList<>();
            answer = Integer.MAX_VALUE;
            for (int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++) {
                    init[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (K == 1) answer = 0;
            else {
                for (int i=0; i<(1<<D); i++) {
                    selected.clear();
                    board = copyBoard();
                    for (int j=0; j<D; j++) {
                        if ((i&(1<<j)) != 0) {
                            selected.add(j);
                        }
                    }
                    if (selected.size() > answer) continue;
                    dfs(0);
                }
            }
            sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb);
    }
    public static int[][] copyBoard() {
        board = new int[D][W];
        for (int i=0; i<D; i++) {
            for (int j=0; j<W; j++) {
                board[i][j] = init[i][j];
            }
        }
        return board;
    }
    public static void dfs(int depth) {
        if (depth == selected.size()) {
            if (check()) {
                answer = Math.min(answer, depth);
            }
            return ;
        }
        for (int value=0; value<=1; value++) {
            for (int j=0; j<W; j++) {
                board[selected.get(depth)][j] = value;
            }
            dfs(depth+1);
        }

    }
    public static boolean check() {
        for (int j=0; j<W; j++) {
            int top = board[0][j];
            int cnt = 1;
            boolean flag = false;
            for (int i=1; i<D; i++) {
                if (board[i][j] == top) {
                    cnt++;
                } else {
                    top = board[i][j];
                    cnt = 1;
                }
                if (cnt >= K) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }
}