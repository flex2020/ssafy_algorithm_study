import java.util.*;
import java.io.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        int[][] xa = new int[N][2]; // x a

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            xa[i][0] = Integer.parseInt(st.nextToken());
            xa[i][1] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(xa, (a,b) ->{
            return a[0]-b[0];
        });

        long answer=0;
        long answerIdx=xa[0][0];
        long result=0;

        long idx=xa[0][0];
        long left=0;
        long right=0;

        // 가장 왼쪽꺼 넣어두기
        for (int i = 0; i < N; i++) {
            right+=xa[i][1];
            answer += Math.abs(xa[i][0] - idx) * xa[i][1];
        }

        result = answer;

        // 반복문 진행
        for (int i = 0; i < N-1; i++) {
            left+=xa[i][1];
            right-=xa[i][1];

            result += left*(xa[i+1][0]-xa[i][0]);
            result -= right*(xa[i+1][0]-xa[i][0]);
            if(left < right){
                answer = result;
                answerIdx = xa[i+1][0];
            }else{
                break;
            }
        }

        System.out.println(answerIdx);
    }
}
