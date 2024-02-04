package 강윤서.boj;
import java.util.*;
import java.io.*;
public class Main_24511 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        Deque<Integer> DQ = new ArrayDeque<>();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            if (A[i] == 0) {
                DQ.offerLast(B[i]);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            DQ.offerFirst(Integer.parseInt(st.nextToken()));
            sb.append(DQ.pollLast() + " ");
        }
        System.out.println(sb);
    }
}
