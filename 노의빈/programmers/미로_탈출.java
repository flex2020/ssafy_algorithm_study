import java.util.*;

class Solution {
    private int N, M;
    private int startX, startY;
    private int[] dx = {-1, 0 , 1, 0}, dy = {0, -1, 0, 1};
    
    private String[] map;
    static class Point {
        int x, y, l, count;
        Point(int x, int y, int l, int count) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.count = count;
        }
    }
    
    
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        map = maps;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (maps[i].charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }
        answer = bfs();
        return answer;
    }
    
    public int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(startX, startY, 0, 0));
        boolean[][][] visited = new boolean[N][M][2];
        visited[startX][startY][0] = true;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            
            if (p.l == 1 && map[p.x].charAt(p.y) == 'E') return p.count;
            
            for (int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nl = p.l;
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx].charAt(ny) == 'X') continue;
                if (map[nx].charAt(ny) == 'L') nl = 1;
                if (visited[nx][ny][nl]) continue;
                
                q.offer(new Point(nx, ny, nl, p.count + 1));
                visited[nx][ny][nl] = true;
            }
        }
        return -1;
    }
}
