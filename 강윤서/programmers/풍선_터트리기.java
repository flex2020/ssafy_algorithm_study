import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int leftMinValue = Integer.MAX_VALUE; // 왼쪽에서 최솟값
        Stack<Integer> stack = new Stack<>(); // stack의 top은 오른쪽의 최솟값
        
        // 오른쪽 최솟값을 스택에 담기 위해 배열 역방향 탐색
        for (int i=a.length-1; i>0; i--) {
            if (stack.isEmpty()) stack.push(a[i]);
            else if (a[i] < stack.peek()) stack.push(a[i]);
        }
        
        for (int i=0; i<a.length; i++) {
            if (!stack.isEmpty() && stack.peek() == a[i]) stack.pop(); // 타겟넘버가 최솟값과 동일할 때 빼기
            if (stack.isEmpty()) answer++; // 더 작은 수가 없음 -> 가능
            else {
                if (stack.peek() < a[i] && leftMinValue < a[i]) continue;
                leftMinValue = Math.min(leftMinValue, a[i]); // 왼쪽 최솟값 갱신
                answer++;
            }
        }
        return answer;
    }
}
