import java.io.*;
import java.util.*;
 
public class Solution5432 {
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            String input = br.readLine();
            int answer = 0;
            int laser = 0; // 레이저 수
            int size = 0;
            List<Integer> list = new ArrayList<>(); // 들어왔을때 레이저 수
            for (int i=0; i<input.length(); i++) {
                // 레이저라면
                if (input.charAt(i) == '(' && input.charAt(i+1) == ')') {
                    laser += 1;
                    i += 1;
                }
                // 여는 괄호인 경우
                else if (input.charAt(i) == '(') {
                    list.add(laser);
                    size += 1;
                }
                // 닫는 괄호인 경우
                else {
                    answer += laser - list.remove(--size) + 1;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
 
}
