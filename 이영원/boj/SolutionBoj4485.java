import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        int weight;
        int x, y;

        public Node(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int[][] map;
    static int N;
    static final int INF = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0}; // 우 하 좌 상

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testNum=1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N==0) break;
            map = new int[N][N];
            int answer=0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = dijkstra();

            System.out.println("Problem " + testNum++ + ": " + answer);
        }
    }

    private static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return a.weight - b.weight;
        });
        pq.offer(new Node(map[0][0], 0, 0));
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = INF;
            }
        }
//        dist[0][0] = map[0][0];
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(dist[node.x][node.y]!=INF) continue;

            dist[node.x][node.y] = node.weight;

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                int cost = dist[node.x][node.y] + map[nx][ny];
                if(cost < dist[nx][ny]){
                    pq.offer(new Node(cost, nx, ny));
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dist[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();


        return dist[N-1][N-1];

    }
}
