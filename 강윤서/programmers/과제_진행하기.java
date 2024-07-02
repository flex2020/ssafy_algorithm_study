import java.util.*;
class Solution {
    static class Work {
        String name;
        int start, playtime;
        Work (String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0; // answer에 접근하기 위한 인덱스
        
        PriorityQueue<Work> PQ = new PriorityQueue<>((p1, p2) -> p1.start - p2.start);
        Stack<Work> stack = new Stack<>();
        
        for (String[] plan : plans) {
            String[] time = plan[1].split(":");
            int startTime = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            PQ.offer(new Work(plan[0], startTime, Integer.parseInt(plan[2])));
        }
        while (!PQ.isEmpty()) {
            Work next = PQ.poll(); // 다음에 들어온 과제
            int time = next.start;
            
            while (!stack.isEmpty()) { // 진행중이던 과제가 있다면
                Work working = stack.pop(); // 진행중이던 과제 가져오기
                if (working.start + working.playtime <= time) { // 진행중이던 과제가 종료되었음
                    answer[idx++] = working.name;
                    if (working.start + working.playtime == time) {  // 새롭게 들어온 과제를 시작해야 함
                        break;
                    } else { // 연쇄적으로 PQ 검사
                        if (!stack.isEmpty()) stack.peek().start = working.start + working.playtime;
                        
                        
                    }
                } else { // 진행중이던 과제를 잠시 멈춤
                    working.playtime -= (time-working.start);
                    working.start = next.start; // 들어온 시점에 맞춰서 중지
                    stack.push(working);
                    break;
                }
            }
            stack.push(next); // 새로운 과제 시작
        }
        while (!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }
        return answer;
    }
}
