import java.io.*;
import java.util.*;

public class Main {
    private static long K, P, N, answer;
    private static final int X = 1000000007;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        N = Long.parseLong(st.nextToken());

        for (int i=0; i<N; i++) {
            K = ((K % X) * (P % X)) % X;
        }
        answer = K;
        System.out.println(answer);
    }
}
