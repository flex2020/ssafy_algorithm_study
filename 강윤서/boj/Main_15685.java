package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_15685 {
    static int N, y, x, answer;
    static List<Integer> dir;
    static int[] dr = {0, -1, 0, 1}, dc = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        visited = new boolean[101][101];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dir = new ArrayList<>();
            dir.add(d);
            visited[y][x] = true;
            y += dr[d];
            x += dc[d];
            visited[y][x] = true;
            move(g);
        }
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
    public static void move(int g) {
        for (int a=1; a<=g; a++) {
            int size = dir.size();
            for (int i=size-1; i>=0; i--) {
                int nd = (dir.get(i)+1)%4;
                visited[y][x] = true;
                y += dr[nd];
                x += dc[nd];
                visited[y][x] = true;
                dir.add(nd);
            }
        }
    }
}