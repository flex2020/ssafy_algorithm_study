package 강윤서.swea;

import java.io.*;
import java.util.*;

public class Solution_1218 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc=1; tc<=10; tc++) {
            int N = Integer.parseInt(br.readLine());
            char[] A = br.readLine().toCharArray();
            for (int i=0; i<N; i++) {
                System.out.println(A[i]);
            }
            int idx = 0, answer = 1;
            List<Character> stack = new ArrayList<>();
            while (answer == 1 && idx < N) {
                if (A[idx] == ')') {
                    if (stack.get(stack.size()-1) != '(') {
                        answer = 0;
                    } else {
                        stack.remove(stack.size()-1);
                    }
                } else if (A[idx] == ']') {
                    if (stack.get(stack.size()-1) != '[') {
                        answer = 0;
                    } else {
                        stack.remove(stack.size()-1);
                    }
                } else if (A[idx] == '}') {
                    if (stack.get(stack.size()-1) != '{') {
                        answer = 0;
                    } else {
                        stack.remove(stack.size()-1);
                    }
                } else if (A[idx] == '>') {
                    if (stack.get(stack.size()-1) != '<') {
                        answer = 0;
                    } else {
                        stack.remove(stack.size()-1);
                    }
                } else {
                    stack.add(A[idx]);
                }
                idx++;
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
        
    }
}
