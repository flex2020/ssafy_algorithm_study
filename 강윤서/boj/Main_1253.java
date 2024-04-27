package 강윤서.boj;
import java.io.*;
import java.util.*;
public class Main_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int answer = 0;
        for (int i=N-1; i>=0; i--) { // A[i] 번째 수 만들기
            int start = 0;
            int end = N-1;
            while (start < end) { // 서로 다른 수
                if (start == i) {
                    start++;
                    continue;
                } else if (end == i) {
                    end--;
                    continue;
                }
                if (A[start] + A[end] == A[i]) {
                    answer++;
                    break;
                } else if (A[start] + A[end] < A[i]) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        System.out.println(answer);
    }
}
