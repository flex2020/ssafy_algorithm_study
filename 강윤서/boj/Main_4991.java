package 강윤서.boj;

import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main_4991 {
    static int N, M, answer, startR, startC, size;
    static char[][] board;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static List<Point> dirty;
    static class State {
        int r, c, value, dist;
        State (int r, int c, int value, int dist) {
            this.r = r;
            this.c = c;
            this.value = value;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return r + " " + c + " " + value + " " + dist;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            answer = -1;
            board = new char[N][M];
            dirty = new ArrayList<>();
            for (int i=0; i<N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j=0; j<M; j++) {
                    board[i][j] = input[j];
                    if (board[i][j] == 'o') {
                        board[i][j] = '.';
                        startR = i;
                        startC = j;
                    } else if (board[i][j] == '*') {
                        dirty.add(new Point(i, j));
                    }
                }
            }
            size = dirty.size();
            visited = new boolean[N][M][1<<size];
            bfs();
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
    public static void bfs() {
        Queue<State> Q = new ArrayDeque<>();
        Q.offer(new State(startR, startC, 0, 0));
        visited[startR][startC][0] = true;
        while (!Q.isEmpty()) {
            State cur = Q.poll();
            if (cur.value == (1<<size) - 1) {
                answer = cur.dist;
                return ;
            }
            for (int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if (board[nr][nc] == 'x') continue;
                if (board[nr][nc] == '*') {
                    int idx = dirty.indexOf(new Point(nr, nc));
                    int newValue = (cur.value | 1 << idx);
                    if (visited[nr][nc][newValue]) continue;
                    Q.offer(new State(nr, nc, newValue, cur.dist + 1));
                    visited[nr][nc][newValue] = true;
                }
                if (board[nr][nc] == '.' && !visited[nr][nc][cur.value]) {
                    Q.offer(new State(nr, nc, cur.value, cur.dist+1));
                    visited[nr][nc][cur.value] = true;
                }
            }
        }
    }
    
}
