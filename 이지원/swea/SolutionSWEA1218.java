import java.util.*;
import java.io.*;

public class SolutionSWEA1218 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        Map<Character, Character> brackets = new HashMap<>();
        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('<', '>');

        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            char[] inputs = br.readLine().toCharArray();

            Stack<Character> stack = new Stack<>();
            int result = 1;
            char input;
            for (int i = 0; i < N; i++) {
                input = inputs[i];
                if (input == '(' || input == '[' || input == '{' || input == '<') {
                    stack.push(input);
                } else {
                    if (stack.empty() || input != brackets.get(stack.pop())) {
                        result = 0;
                        break;
                    }
                }
            }

            System.out.println("#" + testCase + " " + result);
        }
    }
}