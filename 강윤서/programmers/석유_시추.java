import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int N = land.length; // 세로
        int M = land[0].length; // 가로
        
        int answer = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int[] result = new int[M]; // 열의 크기만큼 배열 할당
        boolean[][] visited = new boolean[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (land[i][j] == 1 && !visited[i][j]) { // 석유가 있고 시추되지 않음
                    Queue<int[]> Q = new ArrayDeque<>();
                    Set<Integer> selected = new HashSet<>(); // 해당 석유에 대해 접근할 수 있는 열 저장
                    Q.offer(new int[] {i, j});
                    visited[i][j] = true;
                    int size = 0;
                    while (!Q.isEmpty()) {
                        int[] cur = Q.poll();
                        selected.add(cur[1]);
                        size++;
                        for (int d=0; d<4; d++) {
                            int nr = cur[0] + dr[d];
                            int nc = cur[1] + dc[d];
                            if (0<=nr && nr<N && 0<=nc && nc<M && land[nr][nc] == 1 && !visited[nr][nc]) {
                                Q.offer(new int[] {nr, nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                    for (Integer v : selected) {
                        result[v] += size;
                        answer = Math.max(answer, result[v]);
                    }
                }
            }
        }
        return answer;
    }
}
