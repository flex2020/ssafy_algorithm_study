package 강윤서.boj;

import java.io.*;
import java.util.*;

public class Main_4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] A = new int[250000];
        for (int i = 0; i < 250000; i++) A[i] = i;
        for (int i = 2; i < 250000; i++) {
            if (A[i] == 0) continue;
            for (int j = i + i; j < 250000; j += i) {
                A[j] = 0;
            }
        }
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            int cnt = 0;
            for (int i = N + 1; i <= 2 * N; i++) {
                if (A[i] != 0) cnt++;
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}
