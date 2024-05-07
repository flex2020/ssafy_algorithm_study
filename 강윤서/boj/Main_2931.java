package 강윤서.boj;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main_2931 {
    static int N, M, cnt;
    static char[][] board;
    static boolean[][] visited;
    static char[] type = {'|', '-', '1', '2', '3', '4', '+'};
    static int[][] dir = {{0, 2}, {1, 3}, {1, 2}, {0, 1}, {0, 3}, {2, 3}, {0, 1, 2, 3}};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌
    static Point start, end, answer;
    static Queue<int[]> Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];
        Q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = input[j];
                if (board[i][j] == 'M')
                    start = new Point(i, j);
                if (board[i][j] == 'Z')
                    end = new Point(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '.') continue;
                if (board[i][j] == '+') cnt += 2;
                else cnt++;
            }
        }
        cnt++; // 해커가 지운 거
        visited[start.x][start.y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && board[nx][ny] != 'Z' && board[nx][ny] != '.') {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }

        L:
        for (int i = 0; i < 7; i++) {
            board[answer.x][answer.y] = type[i]; // 찾은 공통 빈칸에 가스관 두고 확인
            for (int s = 0; s < 4; s++) {
                int nx = start.x + dx[s];
                int ny = start.y + dy[s];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (board[nx][ny] == '.' || board[nx][ny] == 'Z') continue;
                Q.offer(new int[]{nx, ny, s, 2});
                while (!Q.isEmpty()) {
                    int[] cur = Q.poll();
                    if (board[cur[0]][cur[1]] == 'Z' && cur[3] >= cnt) {
                        System.out.println((answer.x + 1) + " " + (answer.y + 1) + " " + type[i]);
                        break L;
                    }
                    // 올바른 경로로 왔는지 확인
                    boolean isPossible = false;
                    l:
                    for (int t = 0; t < 7; t++) {
                        if (board[cur[0]][cur[1]] == type[t]) {
                            for (int d = 0; d < dir[t].length; d++) {
                                if (dir[t][d] == (cur[2] + 2) % 4) {
                                    isPossible = true;
                                    break l;
                                }
                            }
                        }
                    }
                    if (!isPossible)
                        continue;

                    for (int t = 0; t < 7; t++) {
                        if (board[cur[0]][cur[1]] == type[t]) {
                            if (t == 6) { // '+'
                                int nnx = cur[0] + dx[cur[2]];
                                int nny = cur[1] + dy[cur[2]];
                                if (0 <= nnx && nnx < N && 0 <= nny && nny < M) {
                                    Q.offer(new int[]{nnx, nny, cur[2], cur[3] + 1});
                                }
                            } else {
                                for (int d = 0; d < dir[t].length; d++) {
                                    if (dir[t][d] == (cur[2] + 2) % 4)
                                        continue;
                                    int nnx = cur[0] + dx[dir[t][d]];
                                    int nny = cur[1] + dy[dir[t][d]];
                                    if (0 <= nnx && nnx < N && 0 <= nny && nny < M) {
                                        Q.offer(new int[]{nnx, nny, dir[t][d], cur[3] + 1});
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public static void dfs(int x, int y) {
        if (board[x][y] == '.') {
            answer = new Point(x, y);
            return;
        }
        for (int i = 0; i < 7; i++) {
            if (board[x][y] == type[i]) {
                for (int j = 0; j < dir[i].length; j++) {
                    int nx = x + dx[dir[i][j]];
                    int ny = y + dy[dir[i][j]];
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        dfs(nx, ny);
                    }
                }
            }
        }
    }
}
/*

3 5
..1-M
1-.4.
Z.23.
 */