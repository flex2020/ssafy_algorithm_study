import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 배달 및 수거 정보 스택에 넣기
        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < deliveries[i]; j++) {
                dStack.push(i + 1);
            }
            
            for (int k = 0; k < pickups[i]; k++) {
                pStack.push(i + 1);
            }
        }
        
        // 정답 구하기
        long answer = 0;
        while (!dStack.isEmpty() || !pStack.isEmpty()) {
            int dDistance = dStack.isEmpty() ? 0 : dStack.peek();
            int pDistance = pStack.isEmpty() ? 0 : pStack.peek();
            
            answer += Math.max(dDistance, pDistance);
            
            // 배달하기
            int cnt = 0;
            while (!dStack.isEmpty() && cnt < cap) {
                dStack.pop();
                cnt++;
            }
            
            // 수거하기
            cnt = 0;
            while (!pStack.isEmpty() && cnt < cap) {
                pStack.pop();
                cnt++;
            }
        }
        
        answer *= 2;
        
        return answer;
    }
}