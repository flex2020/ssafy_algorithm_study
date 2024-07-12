import java.util.*;
import java.awt.Point;

class Solution {
    int[][] visited;
    Map<Integer, Integer> map;
    int N, M;
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new int[N][M];
        map = new TreeMap<>();
        int count = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (land[i][j] == 1 && visited[i][j] == 0) {
                    bfs(land, i, j, count++);
                }
            }
        }
        int answer = 0;
        for (int j=0; j<M; j++) {
            int sum = 0;
            List<Integer> vlist = new ArrayList<>();
            for (int i=0; i<N; i++) {
                if (visited[i][j] != 0 && !vlist.contains(visited[i][j])) {
                    vlist.add(visited[i][j]);
                    sum += map.get(visited[i][j]);
                }
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }
    public void bfs(int[][] land, int sx, int sy, int groupId) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sx, sy));
        visited[sx][sy] = groupId;
        int count = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            for (int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || land[nx][ny] == 0 || visited[nx][ny] != 0) continue;
                q.offer(new Point(nx, ny));
                visited[nx][ny] = groupId;
                count += 1;
            }
        }
        map.put(groupId, count);
    }
}
