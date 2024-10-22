import java.util.*;
class Solution {
    static int N, M, answer = Integer.MAX_VALUE;
    static int rer, rec, ber, bec;
    static boolean[][][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        visited = new boolean[N][M][2];
        int rr = 0;
        int rc = 0;
        int br = 0;
        int bc = 0;
        boolean rEnd = false;
        boolean bEnd = false;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (maze[i][j] == 1) {
                    rr = i;
                    rc = j;
                    visited[i][j][0] = true;
                } else if (maze[i][j] == 2) {
                    br = i;
                    bc = j;
                    visited[i][j][1] = true;
                } else if (maze[i][j] == 3) {
                    rer = i;
                    rec = j;
                } else if (maze[i][j] == 4) {
                    ber = i;
                    bec = j;
                }
            }
        }
        dfs(maze, rr, rc, br, bc, rEnd, bEnd, 0);
        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    public static void dfs(int[][] maze, int rr, int rc, int br, int bc, boolean rEnd, boolean bEnd, int cnt) {
        // System.out.println("red: " + rr + " " + rc + " / blue: " + br + " " +bc + " " + rEnd + " " + bEnd + " / cnt: " + cnt);
        if (rr == rer && rc == rec) rEnd = true;
        if (br == ber && bc == bec) bEnd = true;
        if (rEnd && bEnd) {
            answer = Math.min(answer, cnt);
            return ;
        }
        List<int[]> redPossible = new ArrayList<>();
        List<int[]> bluePossible = new ArrayList<>();
        if (!rEnd) {
            for (int i=0; i<4; i++) {
                int nr = rr + dr[i];
                int nc = rc + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M && maze[nr][nc] != 5 && !visited[nr][nc][0]) {
                    redPossible.add(new int[] {nr, nc});
                }
            }
        } else { // 안 해주면 blue 가 아예 못 움직임 -> 위치 고정
            redPossible.add(new int[] {rr, rc}); 
        }
        if (!bEnd) {
            for (int i=0; i<4; i++) {
                int nr = br + dr[i];
                int nc = bc + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M && maze[nr][nc] != 5 && !visited[nr][nc][1]) {
                    bluePossible.add(new int[] {nr, nc});
                }
            }
        } else { // 안 해주면 red 가 아예 못 움직임 -> 위치 고정
            bluePossible.add(new int[] {br, bc}); 
        }
        for (int[] red : redPossible) {
            for (int[] blue: bluePossible) {
                // 둘이 위치 바꾸기
                if (red[0] == br && red[1] == bc && blue[0] == rr && blue[1] == rc) continue;
                // 둘이 같은 위치로 이동하기
                if (red[0] == blue[0] && red[1] == blue[1]) continue;
                
                visited[red[0]][red[1]][0] = true;
                visited[blue[0]][blue[1]][1] = true;
                dfs(maze, red[0], red[1], blue[0], blue[1], rEnd, bEnd, cnt+1);
                visited[red[0]][red[1]][0] = false;
                visited[blue[0]][blue[1]][1] = false;
            }
        }
    }
}
