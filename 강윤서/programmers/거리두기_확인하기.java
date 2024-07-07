import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (int i=0; i<places.length; i++) {
            String[] place = places[i];
            char[][] board = new char[5][5];
            boolean[][] visited = new boolean[5][5];
            List<int[]> people = new ArrayList<>();
            
            for (int r=0; r<5; r++) {
                char[] input = place[r].toCharArray();
                for (int c=0; c<5; c++) {
                    board[r][c] = input[c];
                    if (board[r][c] == 'P') {
                        people.add(new int[] {r, c});
                    }
                }
            }
            boolean flag = true; // 방 별로 거리두기를 충족한다고 가정
            
            for (int[] person : people) {
                Queue<int[]> Q = new ArrayDeque<>();
                Q.offer(new int[] {person[0], person[1], 0}); // r좌표, c좌표, 거리
                visited[person[0]][person[1]] = true;
                while (!Q.isEmpty() && flag) {
                    int[] cur = Q.poll();
                    if (cur[2] >= 2) break;
                    for (int d=0; d<4; d++) {
                        int nr = cur[0] + dr[d];
                        int nc = cur[1] + dc[d];
                        if (0<=nr && nr<5 && 0<=nc && nc<5 && !visited[nr][nc] && board[nr][nc] != 'X') {
                            Q.offer(new int[] {nr, nc, cur[2]+1});
                            visited[nr][nc] = true;
                            if (board[nr][nc] == 'P') {
                                flag = false;
                            }
                        }
                    }
                }
            }
            answer[i] = flag ? 1 : 0;
        }
        return answer;
    }
}
