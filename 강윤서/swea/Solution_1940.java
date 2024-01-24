package 강윤서.swea;

import java.io.*;
public class Solution_1940 {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int curV = 0; // 현재 속도
            int answer = 0;
            for (int i=0; i<N; i++) {
                String[] cmd = br.readLine().split(" ");
                int type = Integer.parseInt(cmd[0]);
                int a = -1;
                if (type != 0)
                	a = Integer.parseInt(cmd[1]);
                if (type == 1) // 가속
                    curV += a;
               	else if (type == 2) { // 감속
                    curV -= a;
                    if (curV < 0) curV = 0;
                }
                answer += curV;
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}