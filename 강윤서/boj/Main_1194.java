package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_1194 {
    static int N, M, answer = -1;
    static char[][] board;
    static boolean[][][] visited;
    static Queue<Node> Q;
    static class Node {
        int r, c, dist, value;
        Node (int r, int c, int dist, int value) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.value = value; // 비트마스킹 값
        }
    }
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][1<<6];
        Q = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                board[i][j] = input[j];
                if (board[i][j] == '0') {
                    Q.offer(new Node(i, j, 0, 0));
                    board[i][j] = '.';
                }
            }
        }
        while (!Q.isEmpty()) {
            Node cur = Q.poll();
            if (board[cur.r][cur.c] == '1') {
                answer = cur.dist;
                break;
            }
            for (int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if (board[nr][nc] == '#') continue;
                if (board[nr][nc] == '.' || board[nr][nc] == '1') { // 빈칸
                    if (!visited[nr][nc][cur.value]) { // 방문 X
                        Q.offer(new Node(nr, nc, cur.dist + 1, cur.value));
                        visited[nr][nc][cur.value] = true;
                    }
                } else if ('a' <= board[nr][nc] && board[nr][nc] <= 'f') { // 열쇠
                    // 비트마스킹(|)으로 열쇠 획득 표시
                    int newValue = cur.value | (1<<((int)board[nr][nc]-(int)('a')));
                    if (!visited[nr][nc][newValue]) {
                        Q.offer(new Node(nr, nc, cur.dist+1, newValue));
                        visited[nr][nc][newValue] = true;
                    }
                } else if ('A' <= board[nr][nc] && board[nr][nc] <= 'F') { // 문
                    // 비트마스킹 값과 & 연산으로 열쇠가 있는지 확인 -> 있으면 이동 가능
                    if ((cur.value & (1<<((int)board[nr][nc]-(int)'A'))) != 0 && !visited[nr][nc][cur.value]) {
                        Q.offer(new Node(nr, nc, cur.dist+1, cur.value));
                        visited[nr][nc][cur.value] = true;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
