import java.util.*;
import java.io.*;

public class SolutionSWEA7964 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            int[] map = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < map.length; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            int index = -1;
            int result = 0;
            aa: while (index < map.length - D) {
                for (int i = D; i > 0; i--) {
                    if (map[index + i] == 1) {
                        index += i;
                        continue aa;
                    }
                }
                
                index += D;
                result++;
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}