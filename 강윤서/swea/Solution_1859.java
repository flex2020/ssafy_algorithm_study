package 강윤서.swea;

import java.io.*;
public class Solution_1859 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] A = new int[N];
            
            for (int i=0; i<N; i++) {
                A[i] = Integer.parseInt(input[i]);
            }
            long price = A[N-1]; // 현재 판매 가격
            long answer = 0L; // 최대 이익
            int cnt = 0; // 산 개수
            for (int i=N-2; i>=0; i--) {
                if (price < A[i]) { // 그동안 산 거 팔기
                    answer += price * cnt;
                    // 초기화
                    price = A[i];
                    cnt = 0;
                } else {
                    answer -= A[i];
                    cnt++;
                }
            }
            if (cnt != 0) {
                answer += price * cnt;
            }
            
            System.out.printf("#%d %d%n", tc, answer);
            
        }
    }
}
