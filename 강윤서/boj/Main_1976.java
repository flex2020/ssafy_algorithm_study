package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1976 {
    static int N, M;
    static int[][] board;
    static int[] A, parent;
    static boolean flag = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board = new int[N][N];
        A = new int[M];
        parent = new int[N];
        make();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            A[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        for (int i = 1; i < M; i++) {
            if (find(A[0]) != find(A[i])) {
                flag = false;
            }
        }
        if (!flag) System.out.println("NO");
        else System.out.println("YES");
    }

    public static void make() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) return parent[a];
        else return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pa] = pb;
        }
    }
}
