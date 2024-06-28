import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static StringBuilder answer;
    private static List<String> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = new StringBuilder();
        list = new ArrayList<>();
        
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String t = st.nextToken();
            int idx = s.indexOf('X');
            if (idx == -1) idx = s.indexOf('x');
            answer.append((t.charAt(idx) + "").toUpperCase());
        }
        System.out.println(answer);
    }
}
