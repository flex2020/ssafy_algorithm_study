import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        for (int i=0; i<s.length(); i++) {
            String temp = s.substring(i, s.length()) + s.substring(0, i);
            if (check(temp)) answer += 1;
        }
        return answer;
    }
    
    public boolean check(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            // 여는 괄호이면 스택에 넣는다
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            // 닫는 괄호이면 스택에 top을 확인한 후 빼거나 중지시킨다
            else {
                if (stack.isEmpty()) return false;
                else if (c == ')' && stack.peek() == '(' 
                    || c == '}' && stack.peek() == '{'
                    || c == ']' && stack.peek() == '[') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
