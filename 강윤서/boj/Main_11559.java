package 강윤서.boj;

import java.io.*;
import java.util.*;
public class Main_11559 {
    static int answer;
    static boolean diff; // 터졌는지 여부
    static char[][] map;
    static boolean[][] visited;
    static class Position {
        int r, c;
        char color;
        Position (int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        visited = new boolean[12][6];
        for (int i=0; i<12; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<6; j++) {
                map[i][j] = input[j];
            }
        }
        while (true) {
            diff = false;
            for (int i=0; i<12; i++) {
                Arrays.fill(visited[i], false);
            }
            for (int i=11; i>=0; i--) {
                for (int j=0; j<6; j++) {
                    if (!visited[i][j] && map[i][j] != '.') {
                        bfs(i, j);
                    }
                }
            }
            if (!diff) break;
            updateMap();
            answer++;
        }
        System.out.println(answer);
        

    }
    public static void bfs(int r, int c) {
        Queue<Position> Q = new ArrayDeque<>();
        Queue<Position> cantBomb = new ArrayDeque<>(); // 혹시 터지지 못할 때를 대비하여 임시 큐 생성 (방문배열 false로 되돌리기)
        Q.offer(new Position(r, c, map[r][c]));
        visited[r][c] = true;

        int cnt = 0; // 인접한 같은 색의 뿌요 개수
        while (!Q.isEmpty()) {
            Position cur = Q.poll();
            cnt++;
            cantBomb.offer(cur);
            for (int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0<=nr && nr<12 && 0<=nc && nc<6 && map[nr][nc] == cur.color && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    Q.offer(new Position(nr, nc, map[nr][nc]));
                }
            }
        }

        if (cnt >= 4) {
            // 터진다
            diff = true;
        } else {
            // 터지지 못한 것들은 false로 바꿔주기
            while (!cantBomb.isEmpty()) {
                Position cur = cantBomb.poll();
                visited[cur.r][cur.c] = false;
            }
        }
    }
    public static void updateMap() {
        // visited 배열에 true 인 것들을 없애주기
        for (int j=0; j<6; j++) {
            boolean flag = false;
            int startR = 0;
            for (int i=11; i>=0; i--) {
                if (visited[i][j]) {
                    map[i][j] = '.';
                    if (!flag) { // 해당 열의 첫 시작
                        flag = true;
                        startR = i;
                    }
                } else {
                    // 아래에 터진 것이 있기 때문에 아래로 내려줘야 함
                    if (flag) {
                        map[startR][j] = map[i][j];
                        map[i][j] = '.';
                        startR--;
                    }
                }
            }
        }
    }
}
