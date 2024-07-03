import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer = recursive(p);
        return answer;
    }
    
    public String recursive(String w) {
        if (w.equals("")) return "";

        int idx = getIndex(w);
        String u = w.substring(0, idx);
        String v = w.substring(idx, w.length());
        
        if (isRight(u)) return u + recursive(v);

        String temp = "(" + recursive(v) + ")";
        for (int i=1; i<u.length()-1; i++) {
            temp += u.charAt(i) == '(' ? ')' : '(';
        }
        return temp;
    }
    
    public int getIndex(String s) {
        int open = 0, close = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') open += 1;
            else close += 1;
            if (open == close) return i + 1;
        }
        return 0;
    }
    public boolean isRight(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            // 여는 괄호면 스택에 삽입
            if (c == '(') {
                stack.push(c);
                continue;
            }
            // 닫는 괄호이면서 스택이 비어있지 않다면 스택에서 하나빼기
            if (!stack.isEmpty()) {
                stack.pop();
                continue;
            }
            // 닫는 괄호인데 스택이 비어 있으면 문제가 있는 것
            return false;
        }
        return stack.isEmpty();
    }
}
