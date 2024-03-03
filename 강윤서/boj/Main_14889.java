package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_14889 {
    static int N, start, link, answer = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        selected = new boolean[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0);
        System.out.println(answer);
    }
    public static void comb(int idx, int cnt) {
        if (cnt == N/2) {
            calculate();
            return ;
        }
        for (int i=idx; i<N; i++) {
            selected[i] = true;
            comb(i+1, cnt+1);
            selected[i] = false;
        }
    }
    public static void calculate() {
        // true: 스타트팀, false: 링크팀
        start = 0;
        link = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (i == j) continue;
                if (selected[i] && selected[j]) {
                    start += map[i][j];
                } else if (!selected[i] && !selected[j]) {
                    link += map[i][j];
                }
            }
        }
        answer = Math.min(answer, Math.abs(start - link));
    }
}
