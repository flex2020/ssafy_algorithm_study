import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
            Stack<Character> stack = new Stack<>();
            int answer = 1;
            int idx = 1;
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();
            
            // () [] {} <>
            Loop:
            for(int i=0;i<input.length(); i++){
                char c = input.charAt(i);
                char p;
                switch(c){
                    case '(':
                    case '[':
                    case '{':
                    case '<':
                        stack.push(c);
                        break;
                    case ')':
                        p = stack.pop();
                        if(p!='('){
                            answer = 0;
                            break Loop;
                        }
                        break;
                    case ']':
                        p = stack.pop();
                        if(p!='['){
                            answer = 0;
                            break Loop;
                        }
                        break;
                    case '}':
                        p = stack.pop();
                        if(p!='{'){
                            answer = 0;
                            break Loop;
                        }
                        break;
                    case '>':
                        p = stack.pop();
                        if(p!='<'){
                            answer = 0;
                            break Loop;
                        }
                        break;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);

		}
	}
}
