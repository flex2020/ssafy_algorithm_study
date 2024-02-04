import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception
    {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        Deque<Integer> dq = new ArrayDeque<>(); // 큐인것만 넣기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i]=Integer.parseInt(st.nextToken());
            if(A[i]==0) dq.offerLast(B[i]); // 뒤에서부터 넣기
        }

        int M = Integer.parseInt(br.readLine());
        int[] C = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            C[i]=Integer.parseInt(st.nextToken());
            dq.offerFirst(C[i]); // 앞에서부터 넣기
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(dq.pollLast()).append(" "); // 뒤에서 빼기
        }

        System.out.println(sb);

    }
}
