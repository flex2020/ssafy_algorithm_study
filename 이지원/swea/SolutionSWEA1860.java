import java.util.*;
import java.io.*;

class SolutionSWEA1860 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] people = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < people.length; i++) {
                people[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(people);

            String result = " Possible";
            for (int i = 0; i < people.length; i++) {
                if (people[i] / M * K - i < 1) {
                    result = " Impossible";
                    break;
                }
            }

            System.out.println("#" + testCase + result);
        }
    }
}