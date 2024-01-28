import java.util.*;
import java.io.*;

public class SolutionSWEA5432 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            char[] inputs = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();

            int result = 0;
            char preInput = '(';
            for (Character input : inputs) {
                if (input == '(') {
                    stack.push(input);
                } else {
                    stack.pop();
                    if (preInput == '(') {
                        result += stack.size();
                    } else {
                        result++;
                    }
                }

                preInput = input;
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}