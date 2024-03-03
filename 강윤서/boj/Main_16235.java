package 강윤서.boj;

import java.util.*;
import java.io.*;
public class Main_16235 {
    static int N, M, K;
    static int[][] A, map;
    static class Tree {
        int r, c, age;
        Tree (int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
    static PriorityQueue<Tree> PQ;
    static Queue<Tree> deadTree, aliveTree;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        map = new int[N][N];
        PQ = new PriorityQueue<>((t1, t2) -> t1.age - t2.age);
        deadTree = new ArrayDeque<>();
        aliveTree = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(map[i], 5);
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            PQ.offer(new Tree(r, c, age));
        }
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(PQ.size());
    }
    public static void spring() {
        while (!PQ.isEmpty()) {
            Tree t = PQ.poll();
            if (map[t.r][t.c] >= t.age) {
                map[t.r][t.c] -= t.age;
                t.age++;
                aliveTree.offer(t);
            } else {
                deadTree.offer(t);
            }
        }
    }
    public static void summer() {
        while (!deadTree.isEmpty()) {
            Tree t = deadTree.poll();
            map[t.r][t.c] += t.age / 2;
        }
    }
    public static void fall() {
        while (!aliveTree.isEmpty()) {
            Tree t = aliveTree.poll();
            if (t.age % 5 == 0) {
                for (int i=0; i<8; i++) {
                    int nr = t.r + dr[i];
                    int nc = t.c + dc[i];
                    if (0<=nr && nr<N && 0<=nc && nc<N) {
                        PQ.offer(new Tree(nr, nc, 1));
                    }
                }
            }
            PQ.offer(t);
        }
    }
    public static void winter() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }
}
