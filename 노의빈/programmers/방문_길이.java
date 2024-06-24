import java.util.*;

class Solution {
    static class Edge {
        int sx, sy, ex, ey;
        public Edge(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) return false;
            Edge e = (Edge) o;
            if (sx == e.sx && sy == e.sy && ex == e.ex && ey == e.ey) return true;
            return false;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(sx, sy, ex, ey);
        }
    }
    
    public int solution(String dirs) {
        int answer = 0;
        int x = 0, y = 0;
        Set<Edge> visited = new HashSet<>();
        
        for (int i=0; i<dirs.length(); i++) {
            char c = dirs.charAt(i);
            int nx = x, ny = y;
            if (c == 'U') ny += 1;
            else if (c == 'D') ny -= 1;
            else if (c == 'L') nx -= 1;
            else if (c == 'R') nx += 1;
            
            if (Math.abs(nx) > 5 || Math.abs(ny) > 5) continue;
            Edge e1 = new Edge(x, y, nx, ny);
            Edge e2 = new Edge(nx, ny, x, y);
            if (!visited.contains(e1) && !visited.contains(e2)) {
                answer += 1;
                visited.add(e1);
                visited.add(e2);
            }
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}
