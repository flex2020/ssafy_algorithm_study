package 강윤서.swea;

import java.io.*;

public class Solution_5432 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            char[] input = br.readLine().toCharArray();
            int answer = 0;
            int cnt = 0; // 여는 괄호 개수
            for (int i=0; i<input.length; i++) {
                if (input[i] == '(') {
                    cnt += 1;
                } else {
                    cnt -= 1;
                    if (input[i-1] == '(') { // 자르기
                        answer += cnt;
                    } else {
                        answer += 1; // 잘리고 나서 남은 쇠막대기
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, answer);

        }
    }
}
