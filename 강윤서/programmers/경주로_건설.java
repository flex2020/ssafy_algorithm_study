import java.util.*;
class Solution {
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌
    static int[] dc = {0, 1, 0, -1};
    static class Node {
        int r, c, dir, cost;
        Node (int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    // 방향이 바뀔 때 코너가 생긴다. (왔던 길로는 되돌아 갈 필요가 없기 때문)
    // 직선도로 100, 코너 500
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int N = board.length;
        int M = board[0].length; 
        int[][][] visited = new int[N][M][4]; // 방문하는데 드는 비용 저장 : 방향별로 저장하기 위해 3차원 방문배열
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                visited[i][j][0] = Integer.MAX_VALUE;
                visited[i][j][1] = Integer.MAX_VALUE;
                visited[i][j][2] = Integer.MAX_VALUE;
                visited[i][j][3] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Node> PQ = new PriorityQueue<>((p1, p2) -> p1.cost - p2.cost);
        PQ.offer(new Node(0, 0, -1, 0)); // -1: 초기 방향
        visited[0][0][0] = 0;
        visited[0][0][1] = 0;
        visited[0][0][2] = 0;
        visited[0][0][3] = 0;
        while (!PQ.isEmpty()) {
            Node cur = PQ.poll();
            if (cur.r == N-1 && cur.c == M-1) {
                answer = cur.cost;
                break;
            }
            for (int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0<=nr && nr<N && 0<=nc && nc<M && board[nr][nc] == 0) {
                    if (cur.dir != -1 && cur.dir != i) {
                        if (cur.cost+600<=visited[nr][nc][i]) { // 코너
                            PQ.offer(new Node(nr, nc, i, cur.cost+600));
                            visited[nr][nc][i] = cur.cost+600;
                        }
                    } else if (cur.cost+100<=visited[nr][nc][i]){
                        PQ.offer(new Node(nr, nc, i, cur.cost+100));
                        visited[nr][nc][i] = cur.cost+100;
                    }
                }
            }
        }
        return answer;
    }
}
