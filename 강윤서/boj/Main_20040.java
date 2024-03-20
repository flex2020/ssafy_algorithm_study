package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_20040 {
    static int N, M, answer;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        make();
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (union(a, b) && answer == 0) answer = i+1;
        }
        System.out.println(answer);
    }
    public static void make() {
        for (int i=0; i<N; i++) {
            A[i] = i;
        }
    }
    public static boolean union(int a, int b) {
        if (b < a) { // a < b 인 상태 유지
            int temp = a;
            a = b;
            b = temp;
        }
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return true; // 사이클 생성
        if (rootA != rootB) {
            A[rootB] = rootA;
        }
        return false;
    }
    public static int find(int v) {
        if (A[v] == v) return A[v];
        else return A[v] = find(A[v]);
    }
}